package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.JamsRequest;
import alla.verkhohliadova.kcal.dto.response.JamsResponse;
import alla.verkhohliadova.kcal.entity.Jams;
import alla.verkhohliadova.kcal.repository.JamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JamsService {
    @Autowired
    private JamsRepository jamsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(JamsRequest request){
        Jams jams = requestToJams(new Jams(), request);
        jamsRepository.save(jams);
    }

    public void  update (Long id, JamsRequest request){
        Jams jams = requestToJams(findOne(id), request);
        jamsRepository.save(jams);
    }

    public void delete (Long id){
        jamsRepository.delete(findOne(id));
    }

    public List<JamsResponse> findAll(){
        List<Jams> all = jamsRepository.findAll();
        return all.stream().map(this::jamsToJamsResponse).collect(Collectors.toList());
    }

    public Jams findOne(Long id){
        return jamsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Jams with id " + id+ " not exists"));
    }

    private JamsResponse jamsToJamsResponse (Jams jams){
        JamsResponse jamsResponse = new JamsResponse();
        jamsResponse.setId(jams.getId());
        jamsResponse.setName(jams.getName());
        jamsResponse.setProteins(jams.getProteins());
        jamsResponse.setFats(jams.getFats());
        jamsResponse.setCarbohydrates(jams.getCarbohydrates());
        jamsResponse.setKcal(jams.getKcal());
        return jamsResponse;
    }

    private Jams requestToJams (Jams jams, JamsRequest request){
        jams.setName(request.getName());
        jams.setProteins(request.getProteins());
        jams.setFats(request.getFats());
        jams.setCarbohydrates(request.getCarbohydrates());
        jams.setKcal(request.getKcal());
        jams.setProducts(productsService.findOne(request.getProductsId()));
        return jams;
    }
}
