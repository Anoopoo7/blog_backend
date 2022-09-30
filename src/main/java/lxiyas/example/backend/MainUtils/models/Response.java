package lxiyas.example.backend.MainUtils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean status;
    private Object data;
    private String message;
    private String error = null;

    public Response(boolean status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.error = null;
    } 
    
}
