package in.yjawaid.product_service_110524.controllers;

import in.yjawaid.product_service_110524.dtos.ProductResponseDTO;
import in.yjawaid.product_service_110524.model.Product;
import in.yjawaid.product_service_110524.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService)
    {
            this.productService = productService;
    }

    @GetMapping("products/{id}")      //for getting product with id
    public ProductResponseDTO  getProductDetails(@PathVariable("id") int productId)
    {
         return  productService.getSingleProduct(productId);

    }

    @PostMapping( "/products/")   // for adding a new product
    public ProductResponseDTO createNewProduct(@RequestBody ProductResponseDTO productRequestDTO)
    {
         return productService.addProduct(
          productRequestDTO.getTitle(),
                 productRequestDTO.getDescription(),
                 productRequestDTO.getImage(),
                 productRequestDTO.getCategory(),
                 productRequestDTO.getPrice()
         );
    }




}
/*
vghhbjh
hbcb
kvsnv

*/
