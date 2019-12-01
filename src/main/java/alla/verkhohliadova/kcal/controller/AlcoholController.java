package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.AlcoholRequest;
import alla.verkhohliadova.kcal.dto.response.AlcoholResponse;
import alla.verkhohliadova.kcal.entity.Alcohol;
import alla.verkhohliadova.kcal.service.AlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alcohol")
public class AlcoholController {
    @Autowired
    private AlcoholService alcoholService;

    @PostMapping
    public void create(@RequestBody AlcoholRequest request) {
        alcoholService.create(request);
    }

    @GetMapping
    public List<AlcoholResponse> findAll() {
        return alcoholService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody AlcoholRequest request) {
        alcoholService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        alcoholService.delete(id);
    }
}