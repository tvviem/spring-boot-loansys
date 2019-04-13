package vn.blu.tvviem.loansys.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import vn.blu.tvviem.loansys.models.baomat.Role;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Set;


@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds; // 900000ms = 15 minute

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        /*System.out.println(secretKey);
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
        String base64Key = Encoders.BASE64.encode(key.getEncoded());*/
        // change
        /*secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        System.out.println(secretKey);*/
    }

    public String createToken(String username, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        Date now = new Date();
        // Compute time at invalid token
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        return Jwts.builder()//
                .signWith(signingKey, signatureAlgorithm)//
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .compact();
    }
    Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * @param token The bai gui tu client de chung thuc
     * @return true if token valid, else user can not use api resources
     * */
    boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}