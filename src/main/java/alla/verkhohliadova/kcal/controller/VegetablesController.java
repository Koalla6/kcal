package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.VegetablesRequest;
import alla.verkhohliadova.kcal.dto.response.VegetablesResponse;
import alla.verkhohliadova.kcal.entity.Vegetables;
import alla.verkhohliadova.kcal.service.VegetablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vegetables")
public class VegetablesController {
    @Autowired
    private VegetablesService vegetablesService;

    @PostMapping
    public void create(@RequestBody VegetablesRequest request) {
        vegetablesService.create(request);
    }

    @GetMapping
    public List<VegetablesResponse> findAll() {
        return vegetablesService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody VegetablesRequest request) {
        vegetablesService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        vegetablesService.delete(id);
    }
}
