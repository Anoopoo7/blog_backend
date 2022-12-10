package lxiyas.example.backend.PricingEngine.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ecom_pricing_table")
public class PricingTableView {
    @Id
    private String id;
    private String code;
    private int orderTax;
    private int productTax;
    private int deliveryCharge;
    private boolean isFreeDeliveryAvailable;
    private int freeDeliveryQualification;
}
