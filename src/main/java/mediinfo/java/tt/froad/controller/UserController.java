package mediinfo.java.tt.froad.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mediinfo.java.tt.froad.dtos.user.GetUserDto;
import mediinfo.java.tt.froad.repository.UserRepository;
import mediinfo.java.tt.froad.service.UserService;
import mediinfo.java.tt.froad.utils.CopyEntityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "UserController", description = "用户")
@RequiredArgsConstructor
@RequestMapping({"api/v1/user"})
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Operation(summary = "根据用户id获取用户")
    @GetMapping("getByUserId")
    public GetUserDto getUser(Long userId) {
        var user = userRepository.findById(userId);
        return CopyEntityUtil.toEntity(user, GetUserDto.class);
    }
}
