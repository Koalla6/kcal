package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.MushroomsRequest;
import alla.verkhohliadova.kcal.dto.response.MushroomsResponse;
import alla.verkhohliadova.kcal.entity.Mushrooms;
import alla.verkhohliadova.kcal.service.MushroomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mushrooms")
public class MushroomsController {
    @Autowired
    private MushroomsService mushroomsService;

    @PostMapping
    public void create(@RequestBody MushroomsRequest request) {
        mushroomsService.create(request);
    }

    @GetMapping
    public List<MushroomsResponse> findAll() {
        return mushroomsService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody MushroomsRequest request) {
        mushroomsService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        mushroomsService.delete(id);
    }
}
