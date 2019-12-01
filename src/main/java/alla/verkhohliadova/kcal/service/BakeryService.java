package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.BakeryRequest;
import alla.verkhohliadova.kcal.dto.response.BakeryResponse;
import alla.verkhohliadova.kcal.entity.Bakery;
import alla.verkhohliadova.kcal.repository.BakeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BakeryService {
    @Autowired
    private BakeryRepository bakeryRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(BakeryRequest request){
        Bakery bakery = requestToBakery(new Bakery(), request);
        bakeryRepository.save(bakery);
    }

    public void  update (Long id, BakeryRequest request){
        Bakery bakery = requestToBakery(findOne(id), request);
        bakeryRepository.save(bakery);
    }

    public void delete (Long id){
        bakeryRepository.delete(findOne(id));
    }

    public List<BakeryResponse> findAll(){
        List<Bakery> all = bakeryRepository.findAll();
        return all.stream().map(this::bakeryToBakeryResponse).collect(Collectors.toList());
    }

    public Bakery findOne(Long id){
        return bakeryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Bakery with id " + id+ " not exists"));
    }

    private BakeryResponse bakeryToBakeryResponse (Bakery bakery){
        BakeryResponse bakeryResponse = new BakeryResponse();
        bakeryResponse.setId(bakery.getId());
        bakeryResponse.setName(bakery.getName());
        bakeryResponse.setProteins(bakery.getProteins());
        bakeryResponse.setFats(bakery.getFats());
        bakeryResponse.setCarbohydrates(bakery.getCarbohydrates());
        bakeryResponse.setKcal(bakery.getKcal());
        return bakeryResponse;
    }

    private Bakery requestToBakery (Bakery bakery, BakeryRequest request){
        bakery.setName(request.getName());
        bakery.setProteins(request.getProteins());
        bakery.setFats(request.getFats());
        bakery.setCarbohydrates(request.getCarbohydrates());
        bakery.setKcal(request.getKcal());
        bakery.setProducts(productsService.findOne(request.getProductsId()));
        return bakery;
    }
}
