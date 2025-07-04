package mediinfo.java.tt.froad.serviceImpl;

import lombok.RequiredArgsConstructor;
import mediinfo.java.tt.froad.dtos.Rso;
import mediinfo.java.tt.froad.dtos.user.AddUserDto;
import mediinfo.java.tt.froad.dtos.user.GetUserDto;
import mediinfo.java.tt.froad.models.user.UserModel;
import mediinfo.java.tt.froad.repository.UserRepository;
import mediinfo.java.tt.froad.service.UserService;
import mediinfo.java.tt.froad.utils.CopyEntityUtil;
import mediinfo.java.tt.froad.utils.HashEncryptor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public Rso<GetUserDto> addUser(AddUserDto addUserDto) {
        var x1 = userRepository.findByEmail(addUserDto.getEmail());
        if (!x1.isEmpty()) {
            return new Rso<>(false, "邮箱已被占用");
        }
        var x2 = userRepository.findByPhone(addUserDto.getPhone());
        if (!x2.isEmpty()) {
            return new Rso<>(false, "手机号已被占用");
        }

        var user = CopyEntityUtil.toEntity(addUserDto, UserModel.class);
        // 对用户密码进行加密
        user.setPasswordHash(HashEncryptor.encrypt(addUserDto.getPassword()));
        user = userRepository.save(user);
        var result = CopyEntityUtil.toEntity(user, GetUserDto.class);
        return new Rso<>(result);
    }
}
