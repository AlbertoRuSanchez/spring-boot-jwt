package craft.jwt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private Integer customerId;
    private String name;
    private String email;
}
