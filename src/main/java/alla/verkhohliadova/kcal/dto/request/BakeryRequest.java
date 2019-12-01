package alla.verkhohliadova.kcal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BakeryRequest {
    private Long id;
    private String name;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    private Double kcal;

    private Long productsId;
}
