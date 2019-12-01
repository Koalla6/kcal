package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.SeasoningsRequest;
import alla.verkhohliadova.kcal.dto.response.SeasoningsResponse;
import alla.verkhohliadova.kcal.entity.Seasonings;
import alla.verkhohliadova.kcal.service.SeasoningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasonings")
public class SeasoningsController {
    @Autowired
    private SeasoningsService seasoningsService;

    @PostMapping
    public void create(@RequestBody SeasoningsRequest request) {
        seasoningsService.create(request);
    }

    @GetMapping
    public List<SeasoningsResponse> findAll() {
        return seasoningsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody SeasoningsRequest request) {
        seasoningsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        seasoningsService.delete(id);
    }
}
