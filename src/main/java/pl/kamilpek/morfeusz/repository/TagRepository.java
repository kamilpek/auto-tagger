package pl.kamilpek.morfeusz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.kamilpek.morfeusz.model.Tag;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
    Iterable<Tag> findByTag(String tag);
    Iterable<Tag> findByUrls(String url);
}
