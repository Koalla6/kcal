package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.ProductsRequest;
import alla.verkhohliadova.kcal.dto.response.ProductsResponse;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public void  create(ProductsRequest request){
        Products products = requestToProducts(new Products(), request);
        productsRepository.save(products);
    }

    public void  update (Long id, ProductsRequest request){
        Products products = requestToProducts(findOne(id), request);
        productsRepository.save(products);
    }

    public void delete (Long id){ productsRepository.delete(findOne(id));
    }

    public List<ProductsResponse> findAll(){
        List<Products> all = productsRepository.findAll();
        return all.stream().map(ProductsService::productsToProductsResponse).collect(Collectors.toList());
    }

    public Products findOne(Long id){
        return productsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Products with id " + id+ " not exists"));
    }

    public static ProductsResponse productsToProductsResponse (Products products){
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setId(products.getId());
        productsResponse.setName(products.getName());
        return productsResponse;
    }

    private Products requestToProducts (Products products, ProductsRequest request){
        products.setName(request.getName());
        return products;
    }
}
