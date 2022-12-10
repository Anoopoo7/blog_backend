package lxiyas.example.backend.Orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lxiyas.example.backend.Orders.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Order findByUserIdAndStatus(String id, String name);

}
