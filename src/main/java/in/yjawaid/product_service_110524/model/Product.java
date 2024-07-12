package in.yjawaid.product_service_110524.model;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;

}
