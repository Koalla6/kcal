package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.FatsRequest;
import alla.verkhohliadova.kcal.dto.response.FatsResponse;
import alla.verkhohliadova.kcal.entity.Fats;
import alla.verkhohliadova.kcal.service.FatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/fats")
public class FatsController {
    @Autowired
    private FatsService fatsService;

    @PostMapping
    public void create(@RequestBody FatsRequest request) {
        fatsService.create(request);
    }

    @GetMapping
    public List<FatsResponse> findAll() {
        return fatsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody FatsRequest request) {
        fatsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        fatsService.delete(id);
    }
}
