package pl.kamilpek.morfeusz.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "tags")
public class Tag {
    @Id
    private String id;

    private List<String> urls;
    private String tag;
}
