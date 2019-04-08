package vn.blu.tvviem.loansys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /*public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
