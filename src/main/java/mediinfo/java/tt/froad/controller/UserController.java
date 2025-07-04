package mediinfo.java.tt.froad.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mediinfo.java.tt.froad.dtos.Rso;
import mediinfo.java.tt.froad.dtos.user.AddUserDto;
import mediinfo.java.tt.froad.dtos.user.GetUserDto;
import mediinfo.java.tt.froad.repository.UserRepository;
import mediinfo.java.tt.froad.service.UserService;
import mediinfo.java.tt.froad.utils.CopyEntityUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "UserController", description = "用户")
@RequiredArgsConstructor
@RequestMapping({"api/v1/user"})
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Operation(summary = "根据用户id获取用户详情")
    @GetMapping("getByUserId")
    public GetUserDto getUser(Long userId) {
        var user = userRepository.findById(userId);
        return CopyEntityUtil.toEntity(user, GetUserDto.class);
    }

    @Operation(summary = "创建用户")
    @PostMapping
    public Rso<GetUserDto> addUser(@RequestBody AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping
    public Boolean deleteUser(Long userId) {
        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }
        user.softDelete();
        userRepository.save(user);
        return true;
    }

}
