package hu.ponte.hr.controller.mapper;

import hu.ponte.hr.controller.ImageMeta;
import hu.ponte.hr.entity.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentToMetaMapper {

    public ImageMeta map(Document document) {
        return ImageMeta.builder()
                .id(document.getId())
                .name(document.getName())
                .mimeType(document.getMimeType())
                .size(document.getSize())
                .digitalSign(new String(document.getSignature()))
                .build();
    }

    public List<ImageMeta> mapAll(List<Document> documents) {
        return documents.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

}
