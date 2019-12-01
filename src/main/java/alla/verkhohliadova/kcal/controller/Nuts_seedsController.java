package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.Nuts_seedsRequest;
import alla.verkhohliadova.kcal.dto.response.Nuts_seedsResponse;
import alla.verkhohliadova.kcal.entity.Nuts_seeds;
import alla.verkhohliadova.kcal.service.Nuts_seedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nuts_seeds")
public class Nuts_seedsController {
    @Autowired
    private Nuts_seedsService nuts_seedsService;

    @PostMapping
    public void create(@RequestBody Nuts_seedsRequest request) {
        nuts_seedsService.create(request);
    }

    @GetMapping
    public List<Nuts_seedsResponse> findAll() {
        return nuts_seedsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody Nuts_seedsRequest request) {
        nuts_seedsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        nuts_seedsService.delete(id);
    }
}
