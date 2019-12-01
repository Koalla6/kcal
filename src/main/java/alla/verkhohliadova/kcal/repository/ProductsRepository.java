package alla.verkhohliadova.kcal.repository;

import alla.verkhohliadova.kcal.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
