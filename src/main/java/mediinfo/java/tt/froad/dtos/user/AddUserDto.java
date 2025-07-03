package mediinfo.java.tt.froad.dtos.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddUserDto extends UserInfoDto {
    private String password;
}
