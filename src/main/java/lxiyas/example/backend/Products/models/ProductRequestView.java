package lxiyas.example.backend.Products.models;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ProductRequestView {
    private String name;
    private String shortDescription;
    private List<String> images;
    private boolean active;
    private boolean onSale;
    private Map<String, VarientView> varients;
    private boolean infinite;
    private int defaultPrice;
}
