package lxiyas.example.backend.Orders.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lxiyas.example.backend.Orders.utils.OrderStatus;
import lxiyas.example.backend.Orders.utils.PaymentStatus;
import lxiyas.example.backend.PricingEngine.models.PricingInfo;
import lxiyas.example.backend.Users.models.Address;

@Data
@Document("ecom_order")
public class Order {
    @Id
    private String id;
    private String userId;
    private OrderStatus status;
    private boolean active;
    private List<LineItem> lineItems;
    private PricingInfo pricingInfo;
    private Address address;
    private String trackingUrl;
    private PaymentStatus paymentStatus;
    private Date createdDate;
    private Date modifiedDate;
}
