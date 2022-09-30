package lxiyas.example.backend.StaticContent.models;

import java.util.Date;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ecom_static")
public class StaticcontentView {
  @Id
  private String id;
  private String pageType;
  private Map<Object,Object> content;
  private boolean active;
  private Date UpdatedDate;
  private Date createdDate;
}

// {
//     "Home" : {
//         "name":"fhgvjbj",
//         "abc":"sdf"
//     },
//     {
//         ......
//     }
// }
