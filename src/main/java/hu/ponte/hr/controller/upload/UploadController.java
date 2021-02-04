package hu.ponte.hr.controller.upload;

import hu.ponte.hr.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file")
public class UploadController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file") MultipartFile file) {
        documentService.saveFileAsDocument(file);
        return "ok";
    }
}
