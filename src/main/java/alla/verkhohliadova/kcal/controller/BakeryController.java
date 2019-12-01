package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.BakeryRequest;
import alla.verkhohliadova.kcal.dto.response.BakeryResponse;
import alla.verkhohliadova.kcal.service.BakeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/bakery")
public class BakeryController {
    @Autowired
    private BakeryService bakeryService;

    @PostMapping
    public void create(@RequestBody BakeryRequest request) {
        bakeryService.create(request);
    }

    @GetMapping
    public List<BakeryResponse> findAll() {
        return bakeryService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody BakeryRequest request) {
        bakeryService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        bakeryService.delete(id);
    }
}
