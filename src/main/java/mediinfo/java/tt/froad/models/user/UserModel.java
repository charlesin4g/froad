package mediinfo.java.tt.froad.models.user;

import jakarta.persistence.Column;
import jakarta.persistence.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mediinfo.java.tt.froad.models.BaseModel;

/**
 * 用户基本信息表
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "users",
        indexes = {
                @Index(name = "users_username_key", columnList = "username", unique = true),
                @Index(name = "users_email_key", columnList = "email", unique = true)
        })
public class UserModel extends BaseModel {
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Column(name = "height")
    private Short height;

    @Column(name = "weight")
    private Short weight;

    @Column(name = "fitness_level", length = 20)
    private String fitnessLevel;
}
