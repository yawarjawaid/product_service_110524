package in.yjawaid.product_service_110524.repositories;

import in.yjawaid.product_service_110524.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category save(Category category);
    Category findByTitle(String name);
}
