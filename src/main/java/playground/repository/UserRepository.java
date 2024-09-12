package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);
    Boolean existsByUserName(String name);
    User save(User new_user_info);


}