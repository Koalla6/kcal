package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.MushroomsRequest;
import alla.verkhohliadova.kcal.dto.response.MushroomsResponse;
import alla.verkhohliadova.kcal.entity.Mushrooms;
import alla.verkhohliadova.kcal.repository.MushroomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MushroomsService {
    @Autowired
    private MushroomsRepository mushroomsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(MushroomsRequest request){
        Mushrooms mushrooms = requestToMushrooms(new Mushrooms(), request);
        mushroomsRepository.save(mushrooms);
    }

    public void  update (Long id, MushroomsRequest request){
        Mushrooms mushrooms = requestToMushrooms(findOne(id), request);
        mushroomsRepository.save(mushrooms);
    }

    public void delete (Long id){
        mushroomsRepository.delete(findOne(id));
    }

    public List<MushroomsResponse> findAll(){
        List<Mushrooms> all = mushroomsRepository.findAll();
        return all.stream().map(this::mushroomsToMushroomsResponse).collect(Collectors.toList());
    }

    public Mushrooms findOne(Long id){
        return mushroomsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Mushrooms with id " + id+ " not exists"));
    }

    private MushroomsResponse mushroomsToMushroomsResponse (Mushrooms mushrooms){
        MushroomsResponse mushroomsResponse = new MushroomsResponse();
        mushroomsResponse.setId(mushrooms.getId());
        mushroomsResponse.setName(mushrooms.getName());
        mushroomsResponse.setProteins(mushrooms.getProteins());
        mushroomsResponse.setFats(mushrooms.getFats());
        mushroomsResponse.setCarbohydrates(mushrooms.getCarbohydrates());
        mushroomsResponse.setKcal(mushrooms.getKcal());
        return mushroomsResponse;
    }

    private Mushrooms requestToMushrooms (Mushrooms mushrooms, MushroomsRequest request){
        mushrooms.setName(request.getName());
        mushrooms.setProteins(request.getProteins());
        mushrooms.setFats(request.getFats());
        mushrooms.setCarbohydrates(request.getCarbohydrates());
        mushrooms.setKcal(request.getKcal());
        mushrooms.setProducts(productsService.findOne(request.getProductsId()));
        return mushrooms;
    }
}
