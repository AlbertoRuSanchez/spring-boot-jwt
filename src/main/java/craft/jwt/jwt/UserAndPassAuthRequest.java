package craft.jwt.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserAndPassAuthRequest {
    private String username;
    private String password;
}
