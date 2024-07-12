package in.yjawaid.product_service_110524.services;

import in.yjawaid.product_service_110524.dtos.FakeStoreDTO;
import in.yjawaid.product_service_110524.dtos.ProductResponseDTO;
import in.yjawaid.product_service_110524.exceptions.ProductNotFoundException;
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
    public Product getSingleProduct(int productId) throws ProductNotFoundException
    {
        FakeStoreDTO fakeStoreDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreDTO.class);


        if (fakeStoreDTO == null)
            throw new ProductNotFoundException(
                    "Product with id " + productId+ " not found, try a product with id less than 21");

        return fakeStoreDTO.toProduct();


    }

    @Override
   public List<Product> getAllProducts()
   {
        FakeStoreDTO[] fakeStoreDTOList = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreDTO[].class
        );

        // convert all fakestore dto to product object

       List<Product> products = new ArrayList<>();

       for (FakeStoreDTO fakeStoreDTO : fakeStoreDTOList)
       {
           products.add(fakeStoreDTO.toProduct());
       }
        return products;
   }

    @Override  //for adding a new product
    public Product addProduct(
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

         return fakeStoreDTO.toProduct();

    }

}
