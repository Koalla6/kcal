package alla.verkhohliadova.kcal.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
//@Table (name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column (name = "id")
    private Long id;
    //@Column (name = "name")
    private String name;

    @OneToMany(mappedBy = "products")
    private List <Alcohol> alcohol;

    @OneToMany(mappedBy = "products")
    private List <Bakery> bakeries;

    @OneToMany(mappedBy = "products")
    private List <Confectionery> confectioneries;

    @OneToMany(mappedBy = "products")
    private List <Dairy_products> dairy_products;

    @OneToMany(mappedBy = "products")
    private List <Eggs> eggs;

    @OneToMany(mappedBy = "products")
    private List <Fats> fats;

    @OneToMany(mappedBy = "products")
    private List <Fruits> fruits;

    @OneToMany(mappedBy = "products")
    private List <Groats> groats;

    @OneToMany(mappedBy = "products")
    private List <Jams> jams;

    @OneToMany(mappedBy = "products")
    private List <Meat> meats;

    @OneToMany(mappedBy = "products")
    private List <Mushrooms> mushrooms;

    @OneToMany(mappedBy = "products")
    private List <Nuts_seeds> nuts_seeds;

    @OneToMany(mappedBy = "products")
    private List <Sauces> sauces;

    @OneToMany(mappedBy = "products")
    private List <Seafood> seafood;

    @OneToMany(mappedBy = "products")
    private List <Seasonings> seasonings;

    @OneToMany(mappedBy = "products")
    private List <Soft_drinks> soft_drinks;

    @OneToMany(mappedBy = "products")
    private List <Vegetables> vegetables;
}
