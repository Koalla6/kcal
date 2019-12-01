package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.FruitsRequest;
import alla.verkhohliadova.kcal.dto.response.FruitsResponse;
import alla.verkhohliadova.kcal.entity.Fruits;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.repository.FatsRepository;
import alla.verkhohliadova.kcal.repository.FruitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitsService {
    @Autowired
    private FruitsRepository fruitsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(FruitsRequest request){
        Fruits fruits = requestToFruits(new Fruits(), request);
        fruitsRepository.save(fruits);
    }

    public void  update (Long id, FruitsRequest request){
        Fruits fruits = requestToFruits(findOne(id), request);
        fruitsRepository.save(fruits);
    }

    public void delete (Long id){ fruitsRepository.delete(findOne(id));
    }

    public List<FruitsResponse> findAll(){
        List<Fruits> all = fruitsRepository.findAll();
        return all.stream().map(this::fruitsToFruitsResponse).collect(Collectors.toList());
    }

    public Fruits findOne(Long id){
        return fruitsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Fruits with id " + id+ " not exists"));
    }

    private FruitsResponse fruitsToFruitsResponse (Fruits fruits){
        FruitsResponse fruitsResponse = new FruitsResponse();
        fruitsResponse.setId(fruits.getId());
        fruitsResponse.setName(fruits.getName());
        fruitsResponse.setProteins(fruits.getProteins());
        fruitsResponse.setFats(fruits.getFats());
        fruitsResponse.setCarbohydrates(fruits.getCarbohydrates());
        fruitsResponse.setKcal(fruits.getKcal());
        Products products = fruits.getProducts();
        if (products!= null){
            fruitsResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return fruitsResponse;
    }

    private Fruits requestToFruits (Fruits fruits, FruitsRequest request){
        fruits.setName(request.getName());
        fruits.setProteins(request.getProteins());
        fruits.setFats(request.getFats());
        fruits.setCarbohydrates(request.getCarbohydrates());
        fruits.setKcal(request.getKcal());
        fruits.setProducts(productsService.findOne(request.getProductsId()));
        return fruits;
    }
}
