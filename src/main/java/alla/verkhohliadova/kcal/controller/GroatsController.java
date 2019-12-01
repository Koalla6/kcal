package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.GroatsRequest;
import alla.verkhohliadova.kcal.dto.response.GroatsResponse;
import alla.verkhohliadova.kcal.entity.Groats;
import alla.verkhohliadova.kcal.service.GroatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/groats")
public class GroatsController {
    @Autowired
    private GroatsService groatsService;

    @PostMapping
    public void create(@RequestBody GroatsRequest request) {
        groatsService.create(request);
    }

    @GetMapping
    public List<GroatsResponse> findAll() {
        return groatsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody GroatsRequest request) {
        groatsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        groatsService.delete(id);
    }
}
