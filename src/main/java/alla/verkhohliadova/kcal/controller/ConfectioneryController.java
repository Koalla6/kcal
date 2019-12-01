package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.ConfectioneryRequest;
import alla.verkhohliadova.kcal.dto.response.ConfectioneryResponse;
import alla.verkhohliadova.kcal.service.ConfectioneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/confectionery")
public class ConfectioneryController {
    @Autowired
    private ConfectioneryService confectioneryService;

    @PostMapping
    public void create(@RequestBody ConfectioneryRequest request) {
        confectioneryService.create(request);
    }

    @GetMapping
    public List<ConfectioneryResponse> findAll() {
        return confectioneryService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody ConfectioneryRequest request) {
        confectioneryService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        confectioneryService.delete(id);
    }
}
