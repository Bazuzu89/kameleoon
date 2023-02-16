package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
