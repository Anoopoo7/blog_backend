package lxiyas.example.backend.Users.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lxiyas.example.backend.Users.models.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

    List<Address> findAllByUserId(String userId);


}
