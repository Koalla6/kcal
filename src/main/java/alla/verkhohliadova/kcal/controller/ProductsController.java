package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.ProductsRequest;
import alla.verkhohliadova.kcal.dto.response.ProductsResponse;
import alla.verkhohliadova.kcal.entity.Products;
import alla.verkhohliadova.kcal.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @PostMapping
    public void create(@RequestBody ProductsRequest request) {
        productsService.create(request);
    }

    @GetMapping
    public List<ProductsResponse> findAll() {
        return productsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody ProductsRequest request) {
        productsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        productsService.delete(id);
    }
}
