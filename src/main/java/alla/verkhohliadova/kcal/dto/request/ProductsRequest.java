package alla.verkhohliadova.kcal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductsRequest {
    private Long id;
    private String name;

    private Long alcoholId;
    private Long bakeryId;
    private Long confectioneryId;
    private Long dairy_productsId;
    private Long eggsId;
    private Long fatsId;
    private Long fruitsId;
    private Long groatsId;
    private Long jamsId;
    private Long meatId;
    private Long mushroomsId;
    private Long nuts_seedsId;
    private Long saucesId;
    private Long soft_drinksId;
    private Long vegetablesId;
}
