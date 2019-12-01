package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.SaucesRequest;
import alla.verkhohliadova.kcal.dto.response.SaucesResponse;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.entity.Sauces;
import alla.verkhohliadova.kcal.repository.SaucesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaucesService {
    @Autowired
    private SaucesRepository saucesRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(SaucesRequest request){
        Sauces sauces = requestToSauces(new Sauces(), request);
        saucesRepository.save(sauces);
    }

    public void  update (Long id, SaucesRequest request){
        Sauces sauces = requestToSauces(findOne(id), request);
        saucesRepository.save(sauces);
    }

    public void delete (Long id){
        saucesRepository.delete(findOne(id));
    }

    public List<SaucesResponse> findAll(){
        List<Sauces> all = saucesRepository.findAll();
        return all.stream().map(this::saucesToSaucesResponse).collect(Collectors.toList());
    }

    public Sauces findOne(Long id){
        return saucesRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Sauces with id " + id+ " not exists"));
    }

    private SaucesResponse saucesToSaucesResponse (Sauces sauces){
        SaucesResponse saucesResponse = new SaucesResponse();
        saucesResponse.setId(sauces.getId());
        saucesResponse.setName(sauces.getName());
        saucesResponse.setProteins(sauces.getProteins());
        saucesResponse.setFats(sauces.getFats());
        saucesResponse.setCarbohydrates(sauces.getCarbohydrates());
        saucesResponse.setKcal(sauces.getKcal());
        Products products = sauces.getProducts();
        if (products!= null){
            saucesResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return saucesResponse;
    }

    private Sauces requestToSauces (Sauces sauces, SaucesRequest request){
        sauces.setName(request.getName());
        sauces.setProteins(request.getProteins());
        sauces.setFats(request.getFats());
        sauces.setCarbohydrates(request.getCarbohydrates());
        sauces.setKcal(request.getKcal());
        sauces.setProducts(productsService.findOne(request.getProductsId()));
        return sauces;
    }
}
