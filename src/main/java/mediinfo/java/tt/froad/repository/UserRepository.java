package mediinfo.java.tt.froad.repository;

import mediinfo.java.tt.froad.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByPhone(String phone);

    UserModel findByEmail(String email);
}
