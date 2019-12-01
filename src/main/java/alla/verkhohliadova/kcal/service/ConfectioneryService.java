package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.ConfectioneryRequest;
import alla.verkhohliadova.kcal.dto.response.ConfectioneryResponse;
import alla.verkhohliadova.kcal.entity.Confectionery;
import alla.verkhohliadova.kcal.repository.ConfectioneryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfectioneryService {
    @Autowired
    private ConfectioneryRepository confectioneryRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(ConfectioneryRequest request){
        Confectionery confectionery = requestToConfectionery(new Confectionery(), request);
        confectioneryRepository.save(confectionery);
    }

    public void  update (Long id, ConfectioneryRequest request){
        Confectionery confectionery = requestToConfectionery(findOne(id), request);
        confectioneryRepository.save(confectionery);
    }

    public void delete (Long id){ confectioneryRepository.delete(findOne(id));
    }

    public List<ConfectioneryResponse> findAll(){
        List<Confectionery> all = confectioneryRepository.findAll();
        return all.stream().map(this::confectioneryToConfectioneryResponse).collect(Collectors.toList());
    }

    public Confectionery findOne(Long id){
        return confectioneryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Confectionery with id " + id+ " not exists"));
    }

    private ConfectioneryResponse confectioneryToConfectioneryResponse (Confectionery confectionery){
        ConfectioneryResponse confectioneryResponse = new ConfectioneryResponse();
        confectioneryResponse.setId(confectionery.getId());
        confectioneryResponse.setName(confectionery.getName());
        confectioneryResponse.setProteins(confectionery.getProteins());
        confectioneryResponse.setFats(confectionery.getFats());
        confectioneryResponse.setCarbohydrates(confectionery.getCarbohydrates());
        confectioneryResponse.setKcal(confectionery.getKcal());
        return confectioneryResponse;
    }

    private Confectionery requestToConfectionery (Confectionery confectionery, ConfectioneryRequest request){
        confectionery.setName(request.getName());
        confectionery.setProteins(request.getProteins());
        confectionery.setFats(request.getFats());
        confectionery.setCarbohydrates(request.getCarbohydrates());
        confectionery.setKcal(request.getKcal());
        confectionery.setProducts(productsService.findOne(request.getProductsId()));
        return confectionery;
    }
}
