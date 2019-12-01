package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.SeafoodRequest;
import alla.verkhohliadova.kcal.dto.response.SeafoodResponse;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.entity.Seafood;
import alla.verkhohliadova.kcal.repository.SeafoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeafoodService {
    @Autowired
    private SeafoodRepository seafoodRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(SeafoodRequest request){
        Seafood seafood = requestToSeafood(new Seafood(), request);
        seafoodRepository.save(seafood);
    }

    public void  update (Long id, SeafoodRequest request){
        Seafood seafood = requestToSeafood(findOne(id), request);
        seafoodRepository.save(seafood);
    }

    public void delete (Long id){
        seafoodRepository.delete(findOne(id));
    }

    public List<SeafoodResponse> findAll(){
        List<Seafood> all = seafoodRepository.findAll();
        return all.stream().map(this::seafoodToSeafoodResponse).collect(Collectors.toList());
    }

    public Seafood findOne(Long id){
        return seafoodRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Seafood with id " + id+ " not exists"));
    }

    private SeafoodResponse seafoodToSeafoodResponse (Seafood seafood){
        SeafoodResponse seafoodResponse = new SeafoodResponse();
        seafoodResponse.setId(seafood.getId());
        seafoodResponse.setName(seafood.getName());
        seafoodResponse.setProteins(seafood.getProteins());
        seafoodResponse.setFats(seafood.getFats());
        seafoodResponse.setCarbohydrates(seafood.getCarbohydrates());
        seafoodResponse.setKcal(seafood.getKcal());
        Products products = seafood.getProducts();
        if (products!= null){
            seafoodResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return seafoodResponse;
    }

    private Seafood requestToSeafood (Seafood seafood, SeafoodRequest request){
        seafood.setName(request.getName());
        seafood.setProteins(request.getProteins());
        seafood.setFats(request.getFats());
        seafood.setCarbohydrates(request.getCarbohydrates());
        seafood.setKcal(request.getKcal());
        seafood.setProducts(productsService.findOne(request.getProductsId()));
        return seafood;
    }
}
