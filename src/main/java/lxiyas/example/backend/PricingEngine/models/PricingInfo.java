package lxiyas.example.backend.PricingEngine.models;

import lombok.Data;

@Data
public class PricingInfo {
    private int totalPrice;
    private int deliveryCharge;
    private int discount;
    private int netPrice;
    private int tax;
}
