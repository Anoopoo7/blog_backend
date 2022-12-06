package lxiyas.example.backend.Users.models;

import lombok.Data;

@Data
public class Tokens {
    private String userId;
    private String key;
    private String token;
    private String validdate;
    private boolean active;
}
