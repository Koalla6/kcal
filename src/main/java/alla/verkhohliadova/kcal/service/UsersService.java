package alla.verkhohliadova.kcal.service;

import alla.verkhohliadova.kcal.dto.request.UsersRequest;
import alla.verkhohliadova.kcal.dto.response.UsersResponse;
import alla.verkhohliadova.kcal.entity.Users;
import alla.verkhohliadova.kcal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public void  create(UsersRequest request){
        Users users = requestToUsers(new Users(), request);
        usersRepository.save(users);
    }

    public void  update (Long id, UsersRequest request){
        Users users = requestToUsers(findOne(id), request);
        usersRepository.save(users);
    }

    public void delete (Long id){
        usersRepository.delete(findOne(id));
    }

    public List<UsersResponse> findAll(){
        List<Users> all = usersRepository.findAll();
        return all.stream().map(this::usersToUsersResponse).collect(Collectors.toList());
    }

    public Users findOne(Long id){
        return usersRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Users with id " + id+ " not exists"));
    }

    private UsersResponse usersToUsersResponse (Users users){
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(users.getId());
        usersResponse.setName(users.getName());
        usersResponse.setPhoneNumber(users.getPhoneNumber());
        usersResponse.setLogin(users.getLogin());
        usersResponse.setPassword(users.getPassword());
        return usersResponse;
    }

    private Users requestToUsers (Users users, UsersRequest request){
        users.setName(request.getName());
        users.setPhoneNumber(request.getPhoneNumber());
        users.setLogin(request.getLogin());
        users.setPassword(request.getPassword());
        return users;
    }
}
