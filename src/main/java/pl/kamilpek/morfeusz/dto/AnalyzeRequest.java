package pl.kamilpek.morfeusz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeRequest {
    private String url;
    private String text;
}

