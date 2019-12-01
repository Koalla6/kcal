package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.Nuts_seedsRequest;
import alla.verkhohliadova.kcal.dto.response.Nuts_seedsResponse;
import alla.verkhohliadova.kcal.entity.Nuts_seeds;
import alla.verkhohliadova.kcal.repository.Nuts_seedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Nuts_seedsService {
    @Autowired
    private Nuts_seedsRepository nuts_seedsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(Nuts_seedsRequest request){
        Nuts_seeds nuts_seeds = requestToNuts_seeds(new Nuts_seeds(), request);
        nuts_seedsRepository.save(nuts_seeds);
    }

    public void  update (Long id, Nuts_seedsRequest request){
        Nuts_seeds nuts_seeds = requestToNuts_seeds(findOne(id), request);
        nuts_seedsRepository.save(nuts_seeds);
    }

    public void delete (Long id){
        nuts_seedsRepository.delete(findOne(id));
    }

    public List<Nuts_seedsResponse> findAll(){
        List<Nuts_seeds> all = nuts_seedsRepository.findAll();
        return all.stream().map(this::nuts_seedsToNuts_seedsResponse).collect(Collectors.toList());
    }

    public Nuts_seeds findOne(Long id){
        return nuts_seedsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Nuts and seeds with id " + id+ " not exists"));
    }

    private Nuts_seedsResponse nuts_seedsToNuts_seedsResponse (Nuts_seeds nuts_seeds){
        Nuts_seedsResponse nuts_seedsResponse = new Nuts_seedsResponse();
        nuts_seedsResponse.setId(nuts_seeds.getId());
        nuts_seedsResponse.setName(nuts_seeds.getName());
        nuts_seedsResponse.setProteins(nuts_seeds.getProteins());
        nuts_seedsResponse.setFats(nuts_seeds.getFats());
        nuts_seedsResponse.setCarbohydrates(nuts_seeds.getCarbohydrates());
        nuts_seedsResponse.setKcal(nuts_seeds.getKcal());
        return nuts_seedsResponse;
    }

    private Nuts_seeds requestToNuts_seeds (Nuts_seeds nuts_seeds, Nuts_seedsRequest request){
        nuts_seeds.setName(request.getName());
        nuts_seeds.setProteins(request.getProteins());
        nuts_seeds.setFats(request.getFats());
        nuts_seeds.setCarbohydrates(request.getCarbohydrates());
        nuts_seeds.setKcal(request.getKcal());
        nuts_seeds.setProducts(productsService.findOne(request.getProductsId()));
        return nuts_seeds;
    }
}
