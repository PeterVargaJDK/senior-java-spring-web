package hu.ponte.hr.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zoltan
 */
@Getter
@Setter
@Builder
public class ImageMeta {
    private String id;
    private String name;
    private String mimeType;
    private long size;
    private String digitalSign;
}
