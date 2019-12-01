package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.MeatRequest;
import alla.verkhohliadova.kcal.dto.response.MeatResponse;
import alla.verkhohliadova.kcal.entity.Meat;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.repository.MeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeatService {
    @Autowired
    private MeatRepository meatRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(MeatRequest request){
        Meat meat = requestToMeat(new Meat(), request);
        meatRepository.save(meat);
    }

    public void  update (Long id, MeatRequest request){
        Meat meat = requestToMeat(findOne(id), request);
        meatRepository.save(meat);
    }

    public void delete (Long id){
        meatRepository.delete(findOne(id));
    }

    public List<MeatResponse> findAll(){
        List<Meat> all = meatRepository.findAll();
        return all.stream().map(this::meatToMeatResponse).collect(Collectors.toList());
    }

    public Meat findOne(Long id){
        return meatRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Meat with id " + id+ " not exists"));
    }

    private MeatResponse meatToMeatResponse (Meat meat){
        MeatResponse meatResponse = new MeatResponse();
        meatResponse.setId(meat.getId());
        meatResponse.setName(meat.getName());
        meatResponse.setProteins(meat.getProteins());
        meatResponse.setFats(meat.getFats());
        meatResponse.setCarbohydrates(meat.getCarbohydrates());
        meatResponse.setKcal(meat.getKcal());
        Products products = meat.getProducts();
        if (products!= null){
            meatResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return meatResponse;
    }

    private Meat requestToMeat (Meat meat, MeatRequest request){
        meat.setName(request.getName());
        meat.setProteins(request.getProteins());
        meat.setFats(request.getFats());
        meat.setCarbohydrates(request.getCarbohydrates());
        meat.setKcal(request.getKcal());
        meat.setProducts(productsService.findOne(request.getProductsId()));
        return meat;
    }
}
