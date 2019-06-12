package vn.blu.tvviem.loansys.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication.
 * */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        final String expired = (String) request.getAttribute("expired");
        if (expired!=null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, expired);
        } else if(request.getAttribute("invalid_token") != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Token");
        } else if(request.getAttribute("invalid_signature") != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Signature");
        } else if(request.getAttribute("jwt_claims_empty") != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Claim Empty");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Have error when valid token");
        }
        /*logger.error("Responding with unauthorized error. Message - {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());*/
    }
}
