package mediinfo.java.tt.froad.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

public class CopyEntityUtil {

    /**
     * DTO转实体 - 基础方法
     *
     * @param dto         源对象
     * @param entityClass 目标实体类
     * @return 转换后的实体对象
     */
    public static <T, W> T toEntity(W dto, Class<T> entityClass) {
        if (dto == null) {
            return null;
        }

        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Entity conversion failed: " + e.getMessage(), e);
        }
    }

    /**
     * DTO转实体 - 包含属性忽略
     *
     * @param dto              源对象
     * @param entityClass      目标实体类
     * @param ignoreProperties 要忽略的属性名
     * @return 转换后的实体对象
     *
     * 使用示例：
     * // 转换时忽略密码字段
     * User user = CopyEntityUtil.toEntity(loginDto, User.class, "password");
     */
    public static <T, W> T toEntity(W dto, Class<T> entityClass, String... ignoreProperties) {
        if (dto == null) {
            return null;
        }

        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity, ignoreProperties);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Entity conversion failed: " + e.getMessage(), e);
        }
    }

    /**
     * DTO转实体 - 仅复制非空属性
     *
     * @param dto         源对象
     * @param entityClass 目标实体类
     * @return 转换后的实体对象
     *
     * 使用示例：
     * UserDto partialUpdateDto = new UserDto();
     * partialUpdateDto.setEmail("new@example.com"); // 只更新邮箱
     * <p>
     * User existingUser = userRepository.findById(1L).get();
     * User updatedUser = CopyEntityUtil.toEntityNonNull(partialUpdateDto, existingUser.getClass());
     * <p>
     * // 此时updatedUser只有email被更新，其他属性保持不变
     * userRepository.save(updatedUser);
     */
    public static <T, W> T toEntityNonNull(W dto, Class<T> entityClass) {
        if (dto == null) {
            return null;
        }

        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            copyNonNullProperties(dto, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Entity conversion failed: " + e.getMessage(), e);
        }
    }

    /**
     * 批量转换 - DTO列表转实体列表
     *
     * @param dtoList     源对象列表
     * @param entityClass 目标实体类
     * @return 转换后的实体列表
     *
     * 使用示例：
     * List<UserDto> userDtoList = Arrays.asList(
     *     new UserDto("user1", "user1@test.com"),
     *     new UserDto("user2", "user2@test.com")
     * );
     *
     * List<User> userList = CopyEntityUtil.toEntityList(userDtoList, User.class);
     */
    public static <T, W> List<T> toEntityList(List<W> dtoList, Class<T> entityClass) {
        if (dtoList == null || dtoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> entityList = new ArrayList<>();
        for (W dto : dtoList) {
            entityList.add(toEntity(dto, entityClass));
        }
        return entityList;
    }

    /**
     * 实体转DTO - 反向转换
     *
     * @param entity   源实体
     * @param dtoClass 目标DTO类
     * @return 转换后的DTO对象
     */
    public static <T, W> T toDto(W entity, Class<T> dtoClass) {
        return toEntity(entity, dtoClass);
    }

    // 复制非空属性核心方法
    private static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // 获取所有为null的属性名
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            // 检查属性是否为null
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }

    /**
     * 自定义映射转换器
     *
     * @param source        源对象
     * @param target        目标对象
     * @param fieldMappings 字段映射规则
     *                      key: 目标字段名
     *                      value: Function从源对象获取值
     *                      示例:
     *                      toEntity(dto, entity, Map.of(
     *                      "fullName", s -> s.getFirstName() + " " + s.getLastName(),
     *                      "age", s -> calculateAge(s.getBirthDate())
     *                      ));
     * <p>
     *
     * 使用示例：
     *  public class PersonDto {
     *     private String firstName;
     *     private String lastName;
     *     private LocalDate birthDate;
     * }
     * <p>
     * public class PersonEntity {
     *     private String fullName;
     *     private Integer age;
     * }
     * <p>
     * PersonDto dto = new PersonDto("John", "Doe", LocalDate.of(1990, 1, 1));
     * <p>
     * Map<String, Function<PersonDto, Object>> mappings = new HashMap<>();
     * mappings.put("fullName", s -> s.getFirstName() + " " + s.getLastName());
     * mappings.put("age", s -> Period.between(s.getBirthDate(), LocalDate.now()).getYears());
     * <p>
     * PersonEntity entity = new PersonEntity();
     * CopyEntityUtil.customCopy(dto, entity, mappings);
     * <p>
     * // 结果:
     * // entity.fullName = "John Doe"
     * // entity.age = 33 (根据当前年份计算)
     */
    public static <S, T> void customCopy(S source, T target, Map<String, Function<S, Object>> fieldMappings) {
        // 先执行基础属性复制
        BeanUtils.copyProperties(source, target);

        // 应用自定义映射
        fieldMappings.forEach((fieldName, mappingFunction) -> {
            try {
                Field field = target.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(target, mappingFunction.apply(source));
            } catch (Exception e) {
                throw new RuntimeException("Custom mapping failed for field: " + fieldName, e);
            }
        });
    }
}