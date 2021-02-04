package hu.ponte.hr.repository;

import hu.ponte.hr.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, String> {

    List<Document> findByMimeTypeContaining(String mimeType);

}
