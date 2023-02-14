package repository;

import model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
