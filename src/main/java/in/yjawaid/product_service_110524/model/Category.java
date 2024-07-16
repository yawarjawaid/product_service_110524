package in.yjawaid.product_service_110524.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    private String title;
    private String description;

    @OneToMany(mappedBy ="category", fetch = FetchType.EAGER) //similar to join
    //@Fetch(value = FetchMode.JOIN)
    //@Fetch(value = FetchMode.SELECT) //like LAZY
    //@Fetch(value = FetchMode.SUBSELECT)
    List<Product> products;

}
