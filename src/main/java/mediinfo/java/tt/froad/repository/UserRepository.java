package mediinfo.java.tt.froad.repository;

import mediinfo.java.tt.froad.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    List<UserModel> findByPhone(String phone);

    List<UserModel> findByEmail(String email);
}
