package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.SaucesRequest;
import alla.verkhohliadova.kcal.dto.response.SaucesResponse;
import alla.verkhohliadova.kcal.entity.Sauces;
import alla.verkhohliadova.kcal.service.SaucesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/sauces")
public class SaucesController {
    @Autowired
    private SaucesService saucesService;

    @PostMapping
    public void create(@RequestBody SaucesRequest request) {
        saucesService.create(request);
    }

    @GetMapping
    public List<SaucesResponse> findAll() {
        return saucesService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody SaucesRequest request) {
        saucesService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        saucesService.delete(id);
    }
}
