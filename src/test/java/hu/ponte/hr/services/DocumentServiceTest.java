package hu.ponte.hr.services;

import hu.ponte.hr.entity.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Test
    public void testSaveFileAsDocument() {
        byte[] content = "Hello World!".getBytes();
        String mimeType = MediaType.TEXT_PLAIN_VALUE;
        String fileName = "hello.txt";
        String name = "hello";

        MockMultipartFile file = new MockMultipartFile(name, fileName, mimeType, content);

        Document document = documentService.saveFileAsDocument(file);
        assertThat(document.getId(), notNullValue());
        assertThat(document.getContent(), is(content));
        assertThat(document.getMimeType(), is(mimeType));
        assertThat(document.getName(), is(fileName));
        assertThat(document.getSize(), is((long) content.length));

    }

}