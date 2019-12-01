package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.JamsRequest;
import alla.verkhohliadova.kcal.dto.response.JamsResponse;
import alla.verkhohliadova.kcal.entity.Jams;
import alla.verkhohliadova.kcal.service.JamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jams")
public class JamsController {
    @Autowired
    private JamsService jamsService;

    @PostMapping
    public void create(@RequestBody JamsRequest request) {
        jamsService.create(request);
    }

    @GetMapping
    public List<JamsResponse> findAll() {
        return jamsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody JamsRequest request) {
        jamsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        jamsService.delete(id);
    }
}
