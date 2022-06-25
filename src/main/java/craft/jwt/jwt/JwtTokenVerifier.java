package craft.jwt.jwt;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@AllArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if(Strings.isEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
            filterChain.doFilter(request, response);
            return;
        }

        try{
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix() , "");
            SecretKey key = jwtConfig.getSecretKeyForSign();
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            Claims body = claims.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> authority = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authority);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e){
            throw new IllegalStateException("Token cannot be trusted");
        }

        filterChain.doFilter(request, response);

    }
}
