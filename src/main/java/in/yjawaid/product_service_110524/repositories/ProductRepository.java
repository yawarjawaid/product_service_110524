package in.yjawaid.product_service_110524.repositories;

import in.yjawaid.product_service_110524.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(int id);
}
