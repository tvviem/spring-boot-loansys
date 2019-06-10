package vn.blu.tvviem.loansys.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.blu.tvviem.loansys.models.baomat.User;
import vn.blu.tvviem.loansys.repositories.UserRepository;
import vn.blu.tvviem.loansys.security.JwtTokenProvider;
import vn.blu.tvviem.loansys.web.dto.AuthRequestInfo;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/signin")
    public ResponseEntity signin(@RequestBody AuthRequestInfo data) {
        try {
            String username = data.getUsername();
            // Chung thuc thong tin
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            // Find User by username
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(
                    "Not found roles of username[%s]", username)));
            //if (user.isEnabled()) {
            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);
            //}
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}