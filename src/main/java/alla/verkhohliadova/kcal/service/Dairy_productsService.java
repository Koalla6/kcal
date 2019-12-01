package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.Dairy_productsRequest;
import alla.verkhohliadova.kcal.dto.response.Dairy_productsResponse;
import alla.verkhohliadova.kcal.entity.Dairy_products;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.repository.Dairy_productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Dairy_productsService {
    @Autowired
    private Dairy_productsRepository dairy_productsRepository;
    @Autowired
    private ProductsService productsService;

    public void  create(Dairy_productsRequest request){
        Dairy_products dairy_products = requestToDairy_products (new Dairy_products(), request);
        dairy_productsRepository.save(dairy_products);
    }

    public void  update (Long id, Dairy_productsRequest request){
        Dairy_products dairy_products = requestToDairy_products(findOne(id), request);
        dairy_productsRepository.save(dairy_products);
    }

    public void delete (Long id){dairy_productsRepository.delete(findOne(id));
    }

    public List<Dairy_productsResponse> findAll(){
        List<Dairy_products> all = dairy_productsRepository.findAll();
        return all.stream().map(this::dairy_productsToDairy_productsResponse).collect(Collectors.toList());
    }

    public Dairy_products findOne(Long id){
        return dairy_productsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Dairy products with id " + id+ " not exists"));
    }

    private Dairy_productsResponse dairy_productsToDairy_productsResponse (Dairy_products dairy_products){
        Dairy_productsResponse dairy_productsResponse = new Dairy_productsResponse();
        dairy_productsResponse.setId(dairy_products.getId());
        dairy_productsResponse.setName(dairy_products.getName());
        dairy_productsResponse.setProteins(dairy_products.getProteins());
        dairy_productsResponse.setFats(dairy_products.getFats());
        dairy_productsResponse.setCarbohydrates(dairy_products.getCarbohydrates());
        dairy_productsResponse.setKcal(dairy_products.getKcal());
        Products products = dairy_products.getProducts();
        if (products!= null){
            dairy_productsResponse.setProductsResponse(ProductsService.productsToProductsResponse(products));
        }
        return dairy_productsResponse;
    }

    private Dairy_products requestToDairy_products (Dairy_products dairy_products, Dairy_productsRequest request){
        dairy_products.setName(request.getName());
        dairy_products.setProteins(request.getProteins());
        dairy_products.setFats(request.getFats());
        dairy_products.setCarbohydrates(request.getCarbohydrates());
        dairy_products.setKcal(request.getKcal());
        dairy_products.setProducts(productsService.findOne(request.getProductsId()));
        return dairy_products;
    }
}
