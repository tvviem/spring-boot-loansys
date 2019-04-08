package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.baomat.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
