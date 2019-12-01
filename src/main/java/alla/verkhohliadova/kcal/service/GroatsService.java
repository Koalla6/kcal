package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.GroatsRequest;
import alla.verkhohliadova.kcal.dto.response.GroatsResponse;
import alla.verkhohliadova.kcal.entity.Groats;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.repository.GroatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroatsService {
    @Autowired
    private GroatsRepository groatsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(GroatsRequest request){
        Groats groats = requestToGroats(new Groats(), request);
        groatsRepository.save(groats);
    }

    public void  update (Long id, GroatsRequest request){
        Groats groats = requestToGroats(findOne(id), request);
        groatsRepository.save(groats);
    }

    public void delete (Long id){
        groatsRepository.delete(findOne(id));
    }

    public List<GroatsResponse> findAll(){
        List<Groats> all = groatsRepository.findAll();
        return all.stream().map(this::groatsToGroatsResponse).collect(Collectors.toList());
    }

    public Groats findOne(Long id){
        return groatsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Groats with id " + id+ " not exists"));
    }

    private GroatsResponse groatsToGroatsResponse (Groats groats){
        GroatsResponse groatsResponse = new GroatsResponse();
        groatsResponse.setId(groats.getId());
        groatsResponse.setName(groats.getName());
        groatsResponse.setProteins(groats.getProteins());
        groatsResponse.setFats(groats.getFats());
        groatsResponse.setCarbohydrates(groats.getCarbohydrates());
        groatsResponse.setKcal(groats.getKcal());
        Products products = groats.getProducts();
        if (products!= null){
            groatsResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return groatsResponse;
    }

    private Groats requestToGroats (Groats groats, GroatsRequest request){
        groats.setName(request.getName());
        groats.setProteins(request.getProteins());
        groats.setFats(request.getFats());
        groats.setCarbohydrates(request.getCarbohydrates());
        groats.setKcal(request.getKcal());
        groats.setProducts(productsService.findOne(request.getProductsId()));
        return groats;
    }
}
