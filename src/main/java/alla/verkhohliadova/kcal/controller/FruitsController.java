package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.AlcoholRequest;
import alla.verkhohliadova.kcal.dto.request.FruitsRequest;
import alla.verkhohliadova.kcal.dto.response.AlcoholResponse;
import alla.verkhohliadova.kcal.dto.response.FruitsResponse;
import alla.verkhohliadova.kcal.entity.Fruits;
import alla.verkhohliadova.kcal.service.FruitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/fruits")
public class FruitsController {
    @Autowired
    private FruitsService fruitsService;

    @PostMapping
    public void create(@RequestBody FruitsRequest request) {
        fruitsService.create(request);
    }

    @GetMapping
    public List<FruitsResponse> findAll() {
        return fruitsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody FruitsRequest request) {
        fruitsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        fruitsService.delete(id);
    }
}
