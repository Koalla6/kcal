package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.AlcoholRequest;
import alla.verkhohliadova.kcal.dto.response.AlcoholResponse;
import alla.verkhohliadova.kcal.entity.Alcohol;
import alla.verkhohliadova.kcal.repository.AlcoholRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlcoholService {
    @Autowired
    private AlcoholRepository alcoholRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(AlcoholRequest request){
        Alcohol alcohol = requestToAlcohol(new Alcohol(), request);
        alcoholRepository.save(alcohol);
    }

    public void  update (Long id, AlcoholRequest request){
        Alcohol alcohol = requestToAlcohol(findOne(id), request);
        alcoholRepository.save(alcohol);
    }

    public void delete (Long id){
        alcoholRepository.delete(findOne(id));
    }

    public List<AlcoholResponse> findAll(){
        List<Alcohol> all = alcoholRepository.findAll();
        return all.stream().map(this::alcoholToAlcoholResponse).collect(Collectors.toList());
    }

    public Alcohol findOne(Long id){
        return alcoholRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Alcohol with id " + id+ " not exists"));
    }

    private AlcoholResponse alcoholToAlcoholResponse (Alcohol alcohol){
        AlcoholResponse alcoholResponse = new AlcoholResponse();
        alcoholResponse.setId(alcohol.getId());
        alcoholResponse.setName(alcohol.getName());
        alcoholResponse.setProteins(alcohol.getProteins());
        alcoholResponse.setFats(alcohol.getFats());
        alcoholResponse.setCarbohydrates(alcohol.getCarbohydrates());
        alcoholResponse.setKcal(alcohol.getKcal());
        return alcoholResponse;
    }

    private Alcohol requestToAlcohol (Alcohol alcohol, AlcoholRequest request){
        alcohol.setName(request.getName());
        alcohol.setProteins(request.getProteins());
        alcohol.setFats(request.getFats());
        alcohol.setCarbohydrates(request.getCarbohydrates());
        alcohol.setKcal(request.getKcal());
        alcohol.setProducts(productsService.findOne(request.getProductsId()));
        return alcohol;
    }
}
