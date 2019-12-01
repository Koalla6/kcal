package alla.verkhohliadova.kcal.repository;

import alla.verkhohliadova.kcal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
