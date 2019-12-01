package alla.verkhohliadova.kcal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsersRequest {
    private Long id;
    private String name;
    private String phoneNumber;
    private String login;
    private String password;
}
