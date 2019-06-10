package vn.blu.tvviem.loansys.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }
    InvalidJwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}