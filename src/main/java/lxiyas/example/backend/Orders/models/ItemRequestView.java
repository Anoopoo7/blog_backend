package lxiyas.example.backend.Orders.models;

import lombok.Data;

@Data
public class ItemRequestView {
    private String userId;
    private String productId;
    private int quantity;
    private String varient;
}
