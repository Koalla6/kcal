package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.Soft_drinksRequest;
import alla.verkhohliadova.kcal.dto.response.Soft_drinksResponse;
import alla.verkhohliadova.kcal.entity.Soft_drinks;
import alla.verkhohliadova.kcal.service.Soft_drinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/soft_drinks")
public class Soft_drinksController {
    @Autowired
    private Soft_drinksService soft_drinksService;

    @PostMapping
    public void create(@RequestBody Soft_drinksRequest request) {
        soft_drinksService.create(request);
    }

    @GetMapping
    public List<Soft_drinksResponse> findAll() {
        return soft_drinksService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody Soft_drinksRequest request) {
        soft_drinksService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        soft_drinksService.delete(id);
    }
}
