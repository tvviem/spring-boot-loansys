package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.baomat.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
//    User findByUsername(String username);
    Optional<User> findByUsername(String username);
    User findByEmailOrUsername(String email, String username);
    // Set<Role> findByUsername(String username);
}
