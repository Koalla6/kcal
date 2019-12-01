package alla.verkhohliadova.kcal.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table (name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "name")
    private String name;
    @Column (name = "phone number")
    private String phoneNumber;
    @Column (name = "login")
    private String login;
    @Column (name = "password")
    private String password;
}
