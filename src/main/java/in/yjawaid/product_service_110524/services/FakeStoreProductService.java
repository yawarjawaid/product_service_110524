package in.yjawaid.product_service_110524.services;

import in.yjawaid.product_service_110524.dtos.FakeStoreDTO;
import in.yjawaid.product_service_110524.dtos.ProductResponseDTO;
import in.yjawaid.product_service_110524.exceptions.ProductNotFoundException;
import in.yjawaid.product_service_110524.model.Category;
import in.yjawaid.product_service_110524.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
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
    public Product deleteProduct(int productId)
            throws ProductNotFoundException
    {
        ResponseEntity<FakeStoreDTO> responseEntity = restTemplate.exchange(
                "http://fakestoreapi.com/products/" + productId,
                HttpMethod.DELETE,
                null,
                FakeStoreDTO.class
        );
//        FakeStoreDto fakeStoreDto = restTemplate.exchange(
//                "http://fakestoreapi.com/products/" + productId,
//                HttpMethod.DELETE,
//                null,
//                FakeStoreDto.class
//        ).getBody();

        FakeStoreDTO fakeStoreDto = responseEntity.getBody();
        if (fakeStoreDto == null) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
                            +" try to delete a product with id less than 21");
        }

        return fakeStoreDto.toProduct();

    }
    @Override
    public Product updateProduct (
            int productId,
            String title,
            String description,
            String imageUrl,
            String category,
            double price)
            throws ProductNotFoundException {

        FakeStoreDTO requestDto = new FakeStoreDTO();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setImage(imageUrl);
        requestDto.setCategory(category);
        requestDto.setPrice(price);

        // Known Issue: HTTP PATCH is not supported by RestTemplate
        // So below code will NOT work
        // Will throw an exception:
        // Invalid HTTP method: PATCH\n\tat org.springframework.web.client.
        // create request entity to send in patch request body to fakestore
//         HttpEntity<FakeStoreDto> requestEntity = new HttpEntity<>(requestDto);
//
//        ResponseEntity<FakeStoreDto> responseEntity = restTemplate.exchange(
//                "https://fakestoreapi.com/products/" + productId,
//                HttpMethod.PATCH,
//                requestEntity,
//                FakeStoreDto.class
//        );
//
//        FakeStoreDto response = responseEntity.getBody();
//        if (response == null) {
//            throw new ProductNotFoundException(
//                    "Product with id " + productId + " not found");
//        }


        FakeStoreDTO response = requestDto;
        response.setId(productId);
        return response.toProduct();
    }

    @Override
    public Product replaceProduct (
            int productId,
            String title,
            String description,
            String imageUrl,
            String category,
            double price)
            throws ProductNotFoundException {

        FakeStoreDTO requestDto = new FakeStoreDTO();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setImage(imageUrl);
        requestDto.setCategory(category);
        requestDto.setPrice(price);


        // create request entity to send in put request body to fakestore
        HttpEntity<FakeStoreDTO> requestEntity = new HttpEntity<>(requestDto);

        FakeStoreDTO response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreDTO.class
        ).getBody();

        if (response == null) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found" );
        }
        return response.toProduct();

    }

    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortParam) {
        return null;
    }
}
