package alla.verkhohliadova.kcal.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
//@Table (name = "eggs")
public class Eggs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column (name = "id")
    private Long id;
    //@Column (name = "name")
    private String name;
    //@Column (name = "proteins")
    private Double proteins;
    //@Column (name = "fats")
    private Double fats;
    //@Column (name = "carbohydrates")
    private Double carbohydrates;
    //@Column (name = "kcal")
    private Double kcal;

    @ManyToOne
    private Products products;
}
