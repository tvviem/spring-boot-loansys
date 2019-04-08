package vn.blu.tvviem.loansys.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.blu.tvviem.loansys.models.baomat.Role;
import vn.blu.tvviem.loansys.models.baomat.User;
import vn.blu.tvviem.loansys.repositories.RoleRepository;
import vn.blu.tvviem.loansys.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class FirstUserInDb implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public FirstUserInDb() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }*/

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        // Create Role
        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", "Quan ly toan the");
        Role roleTinDung = createRoleIfNotFound("ROLE_TINDUNG", "Quan ly khach hang va ho so");
        User user = createUserIfNotFound("ththe87", "P@ssword87",
                    "ththe87@gmail.com", "The", "Truong Huu",
                        new HashSet<>(Arrays.asList(roleAdmin)));

    }

    /*@Transactional
    Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }*/

    @Transactional
    Role createRoleIfNotFound(String name, String description) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name, description);
        }
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String username, final String password, final String email,
                              final String firstName, final String lastName, final Set<Role> roles) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            //user.isEnabled;
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }
}
