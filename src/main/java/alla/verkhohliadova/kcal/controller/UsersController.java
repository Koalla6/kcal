package alla.verkhohliadova.kcal.controller;

import alla.verkhohliadova.kcal.dto.request.UsersRequest;
import alla.verkhohliadova.kcal.dto.response.UsersResponse;
import alla.verkhohliadova.kcal.entity.Users;
import alla.verkhohliadova.kcal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    public void create(@RequestBody UsersRequest request) {
        usersService.create(request);
    }

    @GetMapping
    public List<UsersResponse> findAll() {
        return usersService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody UsersRequest request) {
        usersService.update(id, request);
    }

    @DeleteMapping (path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        usersService.delete(id);
    }
}
