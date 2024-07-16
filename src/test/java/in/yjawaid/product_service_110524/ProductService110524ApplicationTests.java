package in.yjawaid.product_service_110524;

import in.yjawaid.product_service_110524.model.Category;
import in.yjawaid.product_service_110524.repositories.CategoryRepository;
import in.yjawaid.product_service_110524.repositories.ProductRepository;
import in.yjawaid.product_service_110524.repositories.projections.ProductProjection;
import in.yjawaid.product_service_110524.repositories.projections.ProductWithIdAndTitle;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import in.yjawaid.product_service_110524.model.Product;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductService110524ApplicationTests {


    // 3 ways to DI
    // 1. Constructor - use this
    // 2. Setter method
    // 3 Autowired

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }


    @Test
    void testJpaDeclaredJoin() {
        List<Product> products = productRepository.findAllByCategory_Title("electronics");
        for (Product product : products) {
            System.out.println(product.getTitle());
        }
    }

    @Test
    void testHQL() {
        List<Product> products = productRepository.getProductWithCategoryName("new electronics");
        for (Product product : products) {
            System.out.println(product.getTitle());
            System.out.println(product.getCategory().getTitle());
            System.out.println(product.getPrice());
        }
    }

    @Test
    void testSpecificFields() {
        List<String> productTitles = productRepository.someTitleMethod("new electronics");
        for (String productTitle : productTitles) {
            System.out.println(productTitle);
        }
    }

    @Test
    void testProjections() {
        List<ProductWithIdAndTitle> products = productRepository.someMethod1("new electronics");
        for (ProductWithIdAndTitle product : products) {
            System.out.println(product.getTitle());
            System.out.println(product.getId());
        }

        List<ProductProjection> productProjections = productRepository.someMethod2("new electronics");
        for(ProductProjection p : productProjections) {
            System.out.println(p.getTitle());
            System.out.println(p.getId());
        }

    }
    @Test
    void testNativeSql() {
        //Product product = productRepository.someNativeSql(1);
//        ProductProjection product = productRepository.someNativeSql(1L);
       //System.out.println(product.getTitle());
        ProductProjection product = productRepository.someNativeSql (3);
        System.out.println(product.getTitle());
        System.out.println(product.getId());
    }


    @Test
    @Transactional
    void testFetchType() {
        Optional<Category> category = categoryRepository.findById(4);
        if (category.isPresent()) {
            System.out.println(category.get().getTitle());
            List<Product> products = category.get().getProducts();
            for (Product product : products) {
                System.out.println(product.getTitle());
            }
        }
    }

   @Test
    @Transactional
    void testFetchmode()
    {
        List<Category> categories =categoryRepository.findByTitleEndingWith("electronics");
        for (Category category : categories) {
            System.out.println(category.getTitle());
            List<Product> products = category.getProducts();
            for (Product product : products) {
                System.out.println(product.getTitle());
            }

        }
    }


}
