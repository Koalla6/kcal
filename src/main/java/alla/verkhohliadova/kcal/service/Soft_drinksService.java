package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.Soft_drinksRequest;
import alla.verkhohliadova.kcal.dto.response.Soft_drinksResponse;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.entity.Soft_drinks;
import alla.verkhohliadova.kcal.repository.Soft_drinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Soft_drinksService {
    @Autowired
    private Soft_drinksRepository soft_drinksRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(Soft_drinksRequest request){
        Soft_drinks soft_drinks = requestToSoft_drinks(new Soft_drinks(), request);
        soft_drinksRepository.save(soft_drinks);
    }

    public void  update (Long id, Soft_drinksRequest request){
        Soft_drinks soft_drinks = requestToSoft_drinks(findOne(id), request);
        soft_drinksRepository.save(soft_drinks);
    }

    public void delete (Long id){
        soft_drinksRepository.delete(findOne(id));
    }

    public List<Soft_drinksResponse> findAll(){
        List<Soft_drinks> all = soft_drinksRepository.findAll();
        return all.stream().map(this::soft_drinksToSoft_drinksResponse).collect(Collectors.toList());
    }

    public Soft_drinks findOne(Long id){
        return soft_drinksRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Soft drinks with id " + id+ " not exists"));
    }

    private Soft_drinksResponse soft_drinksToSoft_drinksResponse (Soft_drinks soft_drinks){
        Soft_drinksResponse soft_drinksResponse = new Soft_drinksResponse();
        soft_drinksResponse.setId(soft_drinks.getId());
        soft_drinksResponse.setName(soft_drinks.getName());
        soft_drinksResponse.setProteins(soft_drinks.getProteins());
        soft_drinksResponse.setFats(soft_drinks.getFats());
        soft_drinksResponse.setCarbohydrates(soft_drinks.getCarbohydrates());
        soft_drinksResponse.setKcal(soft_drinks.getKcal());
        Products products = soft_drinks.getProducts();
        if (products!= null){
            soft_drinksResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return soft_drinksResponse;
    }

    private Soft_drinks requestToSoft_drinks (Soft_drinks soft_drinks, Soft_drinksRequest request){
        soft_drinks.setName(request.getName());
        soft_drinks.setProteins(request.getProteins());
        soft_drinks.setFats(request.getFats());
        soft_drinks.setCarbohydrates(request.getCarbohydrates());
        soft_drinks.setKcal(request.getKcal());
        soft_drinks.setProducts(productsService.findOne(request.getProductsId()));
        return soft_drinks;
    }
}
