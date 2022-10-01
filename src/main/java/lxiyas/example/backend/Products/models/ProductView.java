package lxiyas.example.backend.Products.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("ecom_products")
public class ProductView {
    @Id
    private String id;
    private String name;
    private String shortDescription;
    private List<String> images;
    private String landingPageUrl;
    private boolean active;
    private boolean onSale;
    private Date createdDate;
    private Date updatedDate;
}
