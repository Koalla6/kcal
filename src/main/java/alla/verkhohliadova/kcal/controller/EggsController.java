package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.AlcoholRequest;
import alla.verkhohliadova.kcal.dto.request.EggsRequest;
import alla.verkhohliadova.kcal.dto.response.EggsResponse;
import alla.verkhohliadova.kcal.service.EggsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/eggs")
public class EggsController {
    @Autowired
    private EggsService eggsService;

    @PostMapping
    public void create(@RequestBody EggsRequest request) {
        eggsService.create(request);
    }

    @GetMapping
    public List<EggsResponse> findAll() {
        return eggsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody EggsRequest request) {
        eggsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        eggsService.delete(id);
    }
}
