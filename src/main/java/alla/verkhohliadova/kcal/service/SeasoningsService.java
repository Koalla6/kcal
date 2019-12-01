package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.SeasoningsRequest;
import alla.verkhohliadova.kcal.dto.response.SeasoningsResponse;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.entity.Seasonings;
import alla.verkhohliadova.kcal.repository.SeasoningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasoningsService {
    @Autowired
    private SeasoningsRepository seasoningsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(SeasoningsRequest request){
        Seasonings seasonings = requestToSeasonings(new Seasonings(), request);
        seasoningsRepository.save(seasonings);
    }

    public void  update (Long id, SeasoningsRequest request){
        Seasonings seasonings = requestToSeasonings(findOne(id), request);
        seasoningsRepository.save(seasonings);
    }

    public void delete (Long id){
        seasoningsRepository.delete(findOne(id));
    }

    public List<SeasoningsResponse> findAll(){
        List<Seasonings> all = seasoningsRepository.findAll();
        return all.stream().map(this::seasoningsToSeasoningsResponse).collect(Collectors.toList());
    }

    public Seasonings findOne(Long id){
        return seasoningsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Seasonings with id " + id+ " not exists"));
    }

    private SeasoningsResponse seasoningsToSeasoningsResponse (Seasonings seasonings){
        SeasoningsResponse seasoningsResponse = new SeasoningsResponse();
        seasoningsResponse.setId(seasonings.getId());
        seasoningsResponse.setName(seasonings.getName());
        seasoningsResponse.setProteins(seasonings.getProteins());
        seasoningsResponse.setFats(seasonings.getFats());
        seasoningsResponse.setCarbohydrates(seasonings.getCarbohydrates());
        seasoningsResponse.setKcal(seasonings.getKcal());
        Products products = seasonings.getProducts();
        if (products!= null){
            seasoningsResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return seasoningsResponse;
    }

    private Seasonings requestToSeasonings (Seasonings seasonings, SeasoningsRequest request){
        seasonings.setName(request.getName());
        seasonings.setProteins(request.getProteins());
        seasonings.setFats(request.getFats());
        seasonings.setCarbohydrates(request.getCarbohydrates());
        seasonings.setKcal(request.getKcal());
        seasonings.setProducts(productsService.findOne(request.getProductsId()));
        return seasonings;
    }
}
