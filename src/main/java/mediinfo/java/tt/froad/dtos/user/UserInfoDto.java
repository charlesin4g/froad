package mediinfo.java.tt.froad.dtos.user;

import lombok.Data;

@Data
public class UserInfoDto {
    private String username;

    private String email;

    private String phone;

    private Short height;

    private Short weight;

    private String fitnessLevel;
}
