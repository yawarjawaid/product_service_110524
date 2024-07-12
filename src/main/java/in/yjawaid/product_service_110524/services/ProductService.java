package in.yjawaid.product_service_110524.services;

import in.yjawaid.product_service_110524.dtos.FakeStoreDTO;
import in.yjawaid.product_service_110524.dtos.ProductResponseDTO;
import in.yjawaid.product_service_110524.exceptions.ProductNotFoundException;
import in.yjawaid.product_service_110524.model.Product;

import java.util.List;

public interface ProductService {

    public Product  getSingleProduct(int productId) throws ProductNotFoundException; //for getting product with productIfd

    public List<Product> getAllProducts();

    public Product addProduct(
            String title,
            String description,
            String imageUrl,
            String category,
            double price);




}
