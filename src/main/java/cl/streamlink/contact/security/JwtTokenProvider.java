package cl.streamlink.contact.security;

import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
     * microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:5}")
    private long validityInHours = 5; // 1h

    @Autowired
    private MyUserDetails myUserDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email, List<Role> roles) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("auth", roles.stream().map(Role::getAuthority)
                .filter(MiscUtils::isNotEmpty).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInHours * 3600 * 1000);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        try {
            UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (UsernameNotFoundException ex) {
            return null;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {

            String token = bearerToken.substring(6, bearerToken.length()).trim();
            return MiscUtils.isNotEmpty(token) ? token : null;
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
//            throw new ContactApiException("Expired or invalid JWT token", ContactApiError.FORBIDDEN, null);
            return false;
        }
    }

}
