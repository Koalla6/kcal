package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.Dairy_productsRequest;
import alla.verkhohliadova.kcal.dto.response.Dairy_productsResponse;
import alla.verkhohliadova.kcal.service.Dairy_productsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/dairy_products")
public class Dairy_productsController {
    @Autowired
    private Dairy_productsService dairy_productsService;

    @PostMapping
    public void create(@RequestBody Dairy_productsRequest request) {
        dairy_productsService.create(request);
    }

    @GetMapping
    public List<Dairy_productsResponse> findAll() {
        return dairy_productsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody Dairy_productsRequest request) {
        dairy_productsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        dairy_productsService.delete(id);
    }
}
