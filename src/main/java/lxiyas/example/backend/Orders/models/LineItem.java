package lxiyas.example.backend.Orders.models;

import lombok.Data;

@Data
public class LineItem {
    private String productId;
    private String name;
    private int quantity;
    private int price;
    private String varient;
    private String landingPageUrl;
}
