package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.EggsRequest;
import alla.verkhohliadova.kcal.dto.response.EggsResponse;
import alla.verkhohliadova.kcal.entity.Eggs;
import alla.verkhohliadova.kcal.repository.EggsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EggsService {
    @Autowired
    private EggsRepository eggsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(EggsRequest request){
        Eggs eggs = requestToEggs(new Eggs(), request);
        eggsRepository.save(eggs);
    }

    public void  update (Long id, EggsRequest request){
        Eggs eggs = requestToEggs(findOne(id), request);
        eggsRepository.save(eggs);
    }

    public void delete (Long id){
        eggsRepository.delete(findOne(id));
    }

    public List<EggsResponse> findAll(){
        List<Eggs> all = eggsRepository.findAll();
        return all.stream().map(this::eggsToEggsResponse).collect(Collectors.toList());
    }

    public Eggs findOne(Long id){
        return eggsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Eggs with id " + id+ " not exists"));
    }

    private EggsResponse eggsToEggsResponse (Eggs eggs){
        EggsResponse eggsResponse = new EggsResponse();
        eggsResponse.setId(eggs.getId());
        eggsResponse.setName(eggs.getName());
        eggsResponse.setProteins(eggs.getProteins());
        eggsResponse.setFats(eggs.getFats());
        eggsResponse.setCarbohydrates(eggs.getCarbohydrates());
        eggsResponse.setKcal(eggs.getKcal());
        return eggsResponse;
    }

    private Eggs requestToEggs (Eggs eggs, EggsRequest request){
        eggs.setName(request.getName());
        eggs.setProteins(request.getProteins());
        eggs.setFats(request.getFats());
        eggs.setCarbohydrates(request.getCarbohydrates());
        eggs.setKcal(request.getKcal());
        eggs.setProducts(productsService.findOne(request.getProductsId()));
        return eggs;
    }
}
