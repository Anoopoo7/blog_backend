package lxiyas.example.backend.Users.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("ecom_users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String phone;
    private Date createdDate;
    private Date lastOrderDate;
    private Tokens tokens = null;
    private boolean active;
    private boolean firstOrder = false;
    private String password;
}
