package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.SeafoodRequest;
import alla.verkhohliadova.kcal.dto.response.SeafoodResponse;
import alla.verkhohliadova.kcal.entity.Seafood;
import alla.verkhohliadova.kcal.service.SeafoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/seafood")
public class SeafoodController {
    @Autowired
    private SeafoodService seafoodService;

    @PostMapping
    public void create(@RequestBody SeafoodRequest request) {
        seafoodService.create(request);
    }

    @GetMapping
    public List<SeafoodResponse> findAll() {
        return seafoodService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody SeafoodRequest request) {
        seafoodService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        seafoodService.delete(id);
    }
}
