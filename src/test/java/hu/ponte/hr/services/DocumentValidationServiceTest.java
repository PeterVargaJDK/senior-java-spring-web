package hu.ponte.hr.services;

import hu.ponte.hr.entity.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentValidationServiceTest {
    private static final long SIZE_LIMIT = 2097152;

    @Autowired
    private DocumentValidationService validationService;

    @Test
    public void testDocumentSizeOK() {
        Document documentWithSizeLessThanLimit = Document.builder()
                .size(SIZE_LIMIT - 1)
                .build();

        validationService.validateDocument(documentWithSizeLessThanLimit);

        Document documentWithSizeEqualToLimit = Document.builder()
                .size(SIZE_LIMIT)
                .build();

        validationService.validateDocument(documentWithSizeEqualToLimit);
    }

    @Test(expected = ServiceException.class)
    public void testDocumentSizeExceeded() {
        Document documentWithSizeMoreThanLimit = Document.builder()
                .size(SIZE_LIMIT + 1)
                .build();

        validationService.validateDocument(documentWithSizeMoreThanLimit);
    }

}