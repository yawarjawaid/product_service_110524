package in.yjawaid.product_service_110524.dtos;

import in.yjawaid.product_service_110524.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {

    private int id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public ProductResponseDTO toProductResponseDTO()
    {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(id);
        productResponseDTO.setTitle(title);
        productResponseDTO.setDescription(description);
        productResponseDTO.setPrice(price);
        productResponseDTO.setImage(image);
        productResponseDTO.setCategory(category);

        return productResponseDTO;

      }
}
