package lxiyas.example.backend.Products.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lxiyas.example.backend.Products.models.ProductView;

@Repository
public interface ProductRepository extends MongoRepository<ProductView,String> {

    ProductView findByLandingPageUrlAndActive(String product, boolean active);

    Optional<ProductView> findByIdAndActive(String item, boolean active);
    
}
