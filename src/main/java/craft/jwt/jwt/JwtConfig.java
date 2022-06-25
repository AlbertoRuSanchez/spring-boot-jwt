package craft.jwt.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "application.jwt")
@Setter
@Getter
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;

    public SecretKey getSecretKeyForSign() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

}
