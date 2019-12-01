package alla.verkhohliadova.kcal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EggsResponse {
    private Long id;
    private String name;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    private Double kcal;

    @JsonProperty("products")
    private ProductsResponse productsResponse;
}
