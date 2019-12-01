package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.VegetablesRequest;
import alla.verkhohliadova.kcal.dto.response.VegetablesResponse;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.entity.Vegetables;
import alla.verkhohliadova.kcal.repository.VegetablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VegetablesService {
    @Autowired
    private VegetablesRepository vegetablesRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(VegetablesRequest request){
        Vegetables vegetables = requestToVegetables(new Vegetables(), request);
        vegetablesRepository.save(vegetables);
    }

    public void  update (Long id, VegetablesRequest request){
        Vegetables vegetables = requestToVegetables(findOne(id), request);
        vegetablesRepository.save(vegetables);
    }

    public void delete (Long id){
        vegetablesRepository.delete(findOne(id));
    }

    public List<VegetablesResponse> findAll(){
        List<Vegetables> all = vegetablesRepository.findAll();
        return all.stream().map(this::vegetablesToVegetablesResponse).collect(Collectors.toList());
    }

    public Vegetables findOne(Long id){
        return vegetablesRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Vegetables with id " + id+ " not exists"));
    }

    private VegetablesResponse vegetablesToVegetablesResponse (Vegetables vegetables){
        VegetablesResponse vegetablesResponse = new VegetablesResponse();
        vegetablesResponse.setId(vegetables.getId());
        vegetablesResponse.setName(vegetables.getName());
        vegetablesResponse.setProteins(vegetables.getProteins());
        vegetablesResponse.setFats(vegetables.getFats());
        vegetablesResponse.setCarbohydrates(vegetables.getCarbohydrates());
        vegetablesResponse.setKcal(vegetables.getKcal());
        Products products = vegetables.getProducts();
        if (products!= null){
            vegetablesResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return vegetablesResponse;
    }

    private Vegetables requestToVegetables (Vegetables vegetables, VegetablesRequest request){
        vegetables.setName(request.getName());
        vegetables.setProteins(request.getProteins());
        vegetables.setFats(request.getFats());
        vegetables.setCarbohydrates(request.getCarbohydrates());
        vegetables.setKcal(request.getKcal());
        vegetables.setProducts(productsService.findOne(request.getProductsId()));
        return vegetables;
    }
}
