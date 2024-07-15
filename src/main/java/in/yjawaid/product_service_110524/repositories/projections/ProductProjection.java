package in.yjawaid.product_service_110524.repositories.projections;

import in.yjawaid.product_service_110524.model.Category;

import java.math.BigDecimal;

public interface ProductProjection {

    int getId();
    String getTitle();
            String getDescription();
    BigDecimal getPrice();
    Category getCategory();
    String getImageUrl();
}
