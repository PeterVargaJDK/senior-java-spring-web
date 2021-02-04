package hu.ponte.hr.entity;

import hu.ponte.hr.ds.Verifiable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document implements Verifiable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String mimeType;

    private Long size;

    @Lob
    private byte[] content;

    @Transient
    private byte[] signature;

}
