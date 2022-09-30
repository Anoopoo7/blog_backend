package lxiyas.example.backend.StaticContent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lxiyas.example.backend.StaticContent.models.StaticcontentView;

@Repository
public interface StaicContentRepository extends MongoRepository<StaticcontentView,String> {

    StaticcontentView findByPageTypeAndActive(String type, boolean active);
    
}
