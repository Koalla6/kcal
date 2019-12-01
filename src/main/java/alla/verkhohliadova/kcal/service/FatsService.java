package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.FatsRequest;
import alla.verkhohliadova.kcal.dto.response.FatsResponse;
import alla.verkhohliadova.kcal.entity.Fats;
import alla.verkhohliadova.kcal.repository.FatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FatsService {
    @Autowired
    private FatsRepository fatsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(FatsRequest request){
        Fats fats = requestToFats(new Fats(), request);
        fatsRepository.save(fats);
    }

    public void  update (Long id, FatsRequest request){
        Fats fats = requestToFats(findOne(id), request);
        fatsRepository.save(fats);
    }

    public void delete (Long id){
        fatsRepository.delete(findOne(id));
    }

    public List<FatsResponse> findAll(){
        List<Fats> all = fatsRepository.findAll();
        return all.stream().map(this::fatsToFatsResponse).collect(Collectors.toList());
    }

    public Fats findOne(Long id){
        return fatsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Fats with id " + id+ " not exists"));
    }

    private FatsResponse fatsToFatsResponse (Fats fats){
        FatsResponse fatsResponse = new FatsResponse();
        fatsResponse.setId(fats.getId());
        fatsResponse.setName(fats.getName());
        fatsResponse.setProteins(fats.getProteins());
        fatsResponse.setFats(fats.getFats());
        fatsResponse.setCarbohydrates(fats.getCarbohydrates());
        fatsResponse.setKcal(fats.getKcal());
        return fatsResponse;
    }

    private Fats requestToFats (Fats fats, FatsRequest request){
        fats.setName(request.getName());
        fats.setProteins(request.getProteins());
        fats.setFats(request.getFats());
        fats.setCarbohydrates(request.getCarbohydrates());
        fats.setKcal(request.getKcal());
        fats.setProducts(productsService.findOne(request.getProductsId()));
        return fats;
    }
}
