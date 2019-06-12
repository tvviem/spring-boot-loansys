package vn.blu.tvviem.loansys.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Loc tat ca cac yeu cau voi Bearer token
public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        // Phan giai noi dung tu user de lay token
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);

        if (token != null && jwtTokenProvider.validateToken(token, (HttpServletRequest) req)) { // check token valid
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth); // xac nhan duoc thong tin chung thuc
        }
        filterChain.doFilter(req, res);
    }
}
