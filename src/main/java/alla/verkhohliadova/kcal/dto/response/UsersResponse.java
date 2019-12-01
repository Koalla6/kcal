package alla.verkhohliadova.kcal.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsersResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String login;
    private String password;
}
