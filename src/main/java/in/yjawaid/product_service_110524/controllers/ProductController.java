package in.yjawaid.product_service_110524.controllers;

import in.yjawaid.product_service_110524.dtos.ErrorDTO;
import in.yjawaid.product_service_110524.dtos.ProductRequestDTO;
import in.yjawaid.product_service_110524.dtos.ProductResponseDTO;
import in.yjawaid.product_service_110524.exceptions.ProductNotFoundException;
import in.yjawaid.product_service_110524.model.Product;
import in.yjawaid.product_service_110524.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;
    public ProductController(@Qualifier("selfProductService") ProductService productService, ModelMapper modelMapper)
    {
            this.productService = productService;
            this.modelMapper = modelMapper;
    }

    @GetMapping("products/{id}")      //for getting product with id
    public ProductResponseDTO  getProductDetails(@PathVariable("id") int productId)
            throws ProductNotFoundException
    {
         Product product= productService.getSingleProduct(productId);
         //return modelMapper.map(product, ProductResponseDTO.class);
         return covertToProductResponseDto(product);

    }


    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDTO> productResponseDtos = new ArrayList<>();
        for (Product product : products)
        {
            productResponseDtos.add(covertToProductResponseDto(product));
        }


        return productResponseDtos;
    }

    @PostMapping( "/products")   // for adding a new product
    public ResponseEntity<ProductResponseDTO> createNewProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
         /*return productService.addProduct(
          productRequestDTO.getTitle(),
                 productRequestDTO.getDescription(),
                 productRequestDTO.getImage(),
                 productRequestDTO.getCategory(),
                 productRequestDTO.getPrice()
         );*/
        Product product = productService.addProduct(
                productRequestDTO.getTitle(),
                productRequestDTO.getDescription(),
                productRequestDTO.getImageUrl(),
                productRequestDTO.getCategory(),
                productRequestDTO.getPrice()
        );

        //return covertToProductResponseDto(product);
        ProductResponseDTO productResponseDTO = covertToProductResponseDto(product);
        return  new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<ProductResponseDTO> deleteProduct (@PathVariable("id") int productId)
            throws ProductNotFoundException
    {
        Product product = productService.deleteProduct(productId);
        ProductResponseDTO productResponseDTO = covertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);

    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct (@PathVariable("id") int productId,
                                                             @RequestBody ProductRequestDTO productRequestDTO)
            throws ProductNotFoundException

    {
        Product product = productService.updateProduct(productId,
                productRequestDTO.getTitle(),
                productRequestDTO.getDescription(),
                productRequestDTO.getImageUrl(),
                productRequestDTO.getCategory(),
                productRequestDTO.getPrice());

        ProductResponseDTO productResponseDTO = covertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);

    }

@PutMapping("/products/{id}")
public ResponseEntity<ProductResponseDTO> replaceProduct(@PathVariable("id") int productId,
                                                         @RequestBody ProductRequestDTO productRequestDTO)
    throws ProductNotFoundException
{
    Product product = productService.replaceProduct(productId,
            productRequestDTO.getTitle(),
            productRequestDTO.getDescription(),
            productRequestDTO.getImageUrl(),
            productRequestDTO.getCategory(),
            productRequestDTO.getPrice());

    ProductResponseDTO productResponseDTO = covertToProductResponseDto(product);
    return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);


}


    private ProductResponseDTO covertToProductResponseDto (Product product)
    {
        String categoryTitle= product.getCategory().getTitle();
        ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
        productResponseDTO.setCategory(categoryTitle);
        return  productResponseDTO;
    }

    /*@ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException (ProductNotFoundException productNotFoundException)
    {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(productNotFoundException.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }*/

}

