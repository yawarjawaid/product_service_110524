package in.yjawaid.product_service_110524.services;

import in.yjawaid.product_service_110524.exceptions.ProductNotFoundException;
import in.yjawaid.product_service_110524.model.Category;
import in.yjawaid.product_service_110524.model.Product;
import in.yjawaid.product_service_110524.repositories.CategoryRepository;
import in.yjawaid.product_service_110524.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository
    , ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(int productId) throws ProductNotFoundException {
        Product product =productRepository.findByIdIs(productId);

        if(product == null)
        {
            throw new ProductNotFoundException("Product with id "+ product + " not found");
        }

        return product;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(String title,
                              String description,
                              String imageUrl,
                              String category, double price) {

        Product newProduct = new Product();
        newProduct.setTitle(title);
        newProduct.setDescription(description);
        newProduct.setImageUrl(imageUrl);
        newProduct.setPrice(price);

        Category categoryfromDb = categoryRepository.findByTitle(category);

        if (categoryfromDb == null)
        {
            Category newCategory = new Category();
            newCategory.setTitle(category);
            //categoryRepository.save(newCategory);
            categoryfromDb = newCategory;
        }

        newProduct.setCategory(categoryfromDb);
        Product savedProduct = productRepository.save(newProduct);
        return savedProduct;


    }

    @Override
    public Product deleteProduct(int productId) throws ProductNotFoundException {
        Product product = productRepository.findByIdIs(productId);
        if (product == null) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(int productId, String title, String description, String imageUrl, String category, double price)
            throws ProductNotFoundException {

        Product productInDB = productRepository.findByIdIs(productId);

        if (productInDB == null) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }

        if(title != null)
        {
            productInDB.setTitle(title);
        }
        if (description != null)
        {
            productInDB.setDescription(description);
        }
        if (imageUrl != null)
        {
            productInDB.setImageUrl(imageUrl);
        }

        if (price != 0)
            productInDB.setPrice(price);


        if(category != null)
        {
            Category categoryfromDb = categoryRepository.findByTitle(category);
            if( categoryfromDb == null)
            {
                Category newCategory = new Category();
                newCategory.setTitle(category);
                categoryfromDb = newCategory;
            }
            productInDB.setCategory(categoryfromDb);
        }
        return productRepository.save(productInDB);
    }


    @Override
    public Product replaceProduct(int productId, String title, String description, String imageUrl, String category, double price) throws ProductNotFoundException {
        return null;
    }
}
