package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.baomat.Role;
import vn.blu.tvviem.loansys.models.baomat.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
