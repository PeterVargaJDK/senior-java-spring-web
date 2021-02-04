package hu.ponte.hr.services;

import hu.ponte.hr.entity.Document;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class DocumentValidationService {
    private static final int SIZE_LIMIT = 2097152;

    private static final List<Predicate<Document>> predicates = Arrays.asList(
            e -> e.getSize() <= SIZE_LIMIT
    );

    public void validateDocument(Document document) {
        if (document.getSize() > SIZE_LIMIT) {
            throw new ServiceException("Maximum size exceeded");
        }

    }

}
