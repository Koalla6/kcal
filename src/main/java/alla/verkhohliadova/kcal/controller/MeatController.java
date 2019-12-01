package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.MeatRequest;
import alla.verkhohliadova.kcal.dto.response.MeatResponse;
import alla.verkhohliadova.kcal.entity.Meat;
import alla.verkhohliadova.kcal.service.MeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meat")
public class MeatController {
    @Autowired
    private MeatService meatService;

    @PostMapping
    public void create(@RequestBody MeatRequest request) {
        meatService.create(request);
    }

    @GetMapping
    public List<MeatResponse> findAll() {
        return meatService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody MeatRequest request) {
        meatService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        meatService.delete(id);
    }
}
