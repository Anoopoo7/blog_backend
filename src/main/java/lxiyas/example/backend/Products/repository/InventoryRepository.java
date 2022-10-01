package lxiyas.example.backend.Products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lxiyas.example.backend.Products.models.InventoryView;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryView,String> {

    InventoryView findByProductIdAndActive(String id, boolean active);
    
}
