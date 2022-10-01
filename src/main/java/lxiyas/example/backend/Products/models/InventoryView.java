package lxiyas.example.backend.Products.models;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("ecom_inventory")
public class InventoryView {
    @Id
    private String id;
    private String productId;
    private Map<String, VarientView> varients;
    private boolean infinite;
    private boolean active;
    private Date createdDate;
    private Date updatedDate;
}
