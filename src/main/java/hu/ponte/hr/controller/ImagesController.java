package hu.ponte.hr.controller;


import hu.ponte.hr.controller.mapper.DocumentToMetaMapper;
import hu.ponte.hr.entity.Document;
import hu.ponte.hr.repository.DocumentRepository;
import hu.ponte.hr.services.ServiceException;
import hu.ponte.hr.services.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/images")
public class ImagesController {

    private static final String IMAGE = "image";

    @Autowired
    private SignService signService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentToMetaMapper mapper;

    @GetMapping("meta")
    public List<ImageMeta> listImages() {
        List<Document> images = documentRepository.findByMimeTypeContaining(IMAGE);

        signService.signAll(images);

        List<ImageMeta> metaList = mapper.mapAll(images);

        return metaList;
    }

    @GetMapping("preview/{id}")
    public void getImage(@PathVariable("id") String id, HttpServletResponse response) {
        try {
            Document image = documentRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Cannot find document with the specified id."));

            response.setContentType(image.getMimeType());

            ServletOutputStream out = response.getOutputStream();
            out.write(image.getContent());
        } catch (IOException e) {
            throw new ServiceException("Error happened while writing response.", e);
        }
    }

}
