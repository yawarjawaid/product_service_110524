package in.yjawaid.product_service_110524.services;

import in.yjawaid.product_service_110524.dtos.FakeStoreDTO;
import in.yjawaid.product_service_110524.dtos.ProductResponseDTO;
import in.yjawaid.product_service_110524.model.Category;
import in.yjawaid.product_service_110524.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate =restTemplate;
    }

    @Override  // for getting product with productId
    public ProductResponseDTO getSingleProduct(int productId)
    {
        FakeStoreDTO fakeStoreDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreDTO.class);

        return fakeStoreDTO.toProductResponseDTO();
    }



    @Override  //for adding a new product
    public ProductResponseDTO addProduct(
            String title,
            String description,
            String imageUrl,
            String category,
            double price)
    {
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();

        fakeStoreDTO.setTitle(title);
        fakeStoreDTO.setDescription(description);
        fakeStoreDTO.setImage(imageUrl);
        fakeStoreDTO.setCategory(category);
        fakeStoreDTO.setPrice(price);

         FakeStoreDTO response = restTemplate.postForObject(
                 "https://fakestoreapi.com/products/",
                fakeStoreDTO,
                 FakeStoreDTO.class
                 );

         return fakeStoreDTO.toProductResponseDTO();

    }

}
