package hu.ponte.hr.services;

import hu.ponte.hr.entity.Document;
import hu.ponte.hr.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentValidationService validationService;

    public Document saveFileAsDocument(MultipartFile file) {
        try {
            Document document = Document.builder()
                    .name(file.getOriginalFilename())
                    .mimeType(file.getContentType())
                    .content(file.getBytes())
                    .size(file.getSize())
                    .build();

            validationService.validateDocument(document);

            return documentRepository.save(document);
        } catch (IOException e) {
            throw new ServiceException("File cannot be read.", e);
        }
    }

}
