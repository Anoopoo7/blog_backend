package lxiyas.example.backend.Users.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ecom_address")
public class Address {
    @Id
    private String id;
    private String userId;
    private String city;
    private String area;
    private String buildingNo;
    private String pincode;
    private String phoneOne;
    private String phoneTwo;
    private String state;
    private boolean defaults;
}
