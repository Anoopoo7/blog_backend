package lxiyas.example.backend.PricingEngine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lxiyas.example.backend.PricingEngine.models.PricingTableView;

@Repository
public interface PricingTableRepository extends MongoRepository<PricingTableView,String> {

    PricingTableView findByCode(String pricing);
    
}
