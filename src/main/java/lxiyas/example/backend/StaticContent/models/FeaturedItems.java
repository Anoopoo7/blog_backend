package lxiyas.example.backend.StaticContent.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeaturedItems {
    private List<String>  items;
}
