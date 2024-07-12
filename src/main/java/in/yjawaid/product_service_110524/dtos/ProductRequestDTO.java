package in.yjawaid.product_service_110524.dtos;

import in.yjawaid.product_service_110524.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    private int id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
