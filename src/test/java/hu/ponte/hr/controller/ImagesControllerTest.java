package hu.ponte.hr.controller;

import hu.ponte.hr.SignTest;
import hu.ponte.hr.entity.Document;
import hu.ponte.hr.services.DocumentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImagesControllerTest {

    private static final String DUMMY_SIGNATURE = "XYZ+wXKNd3Hpnjxy4vIbBQVD7q7i0t0r9tzpmf1KmyZAEUvpfV8AKQlL7us66rvd6eBzFlSaq5HGVZX2DYTxX1C5fJlh3T3QkVn2zKOfPHDWWItdXkrccCHVR5HFrpGuLGk7j7XKORIIM+DwZKqymHYzehRvDpqCGgZ2L1Q6C6wjuV4drdOTHps63XW6RHNsU18wHydqetJT6ovh0a8Zul9yvAyZeE4HW7cPOkFCgll5EZYZz2iH5Sw1NBNhDNwN2KOxrM4BXNUkz9TMeekjqdOyyWvCqVmr5EgssJe7FAwcYEzznZV96LDkiYQdnBTO8jjN25wlnINvPrgx9dN/Xg==";

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ImagesController imagesController;

    private Document dummyDocument;

    @Before
    public void setup() throws IOException {
        try (InputStream image = SignTest.class.getResourceAsStream("/images/cat.jpg")) {
            byte[] content = image.readAllBytes();
            String mimeType = MediaType.IMAGE_JPEG_VALUE;
            String fileName = "cat.jpg";
            String name = "cat";

            MockMultipartFile cat = new MockMultipartFile(name, fileName, mimeType, content);
            dummyDocument = documentService.saveFileAsDocument(cat);
        }
    }

    @Test
    public void testList() throws Exception {
        List<ImageMeta> imageMetas = imagesController.listImages();
        assertThat(imageMetas, hasSize(1));

        ImageMeta imageMeta = imageMetas.get(0);
        assertThat(imageMeta.getDigitalSign(), is(DUMMY_SIGNATURE));
        assertThat(imageMeta.getId(), is(dummyDocument.getId()));
        assertThat(imageMeta.getMimeType(), is(dummyDocument.getMimeType()));
        assertThat(imageMeta.getName(), is(dummyDocument.getName()));
        assertThat(imageMeta.getSize(), is(dummyDocument.getSize()));

    }

}