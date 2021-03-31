package pl.kamilpek.morfeusz.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kamilpek.morfeusz.TagsFacade;
import pl.kamilpek.morfeusz.dto.AnalyzeRequest;
import pl.kamilpek.morfeusz.dto.GetTagsRequest;
import pl.kamilpek.morfeusz.model.Tag;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    TagsFacade tagsFacade;

    @PostMapping("/analyze")
    public ResponseEntity<Boolean> analyze(@RequestBody AnalyzeRequest analyzeRequest) {
        log.info("AnalyzeRequest [{}]", analyzeRequest.toString());
        try {
            boolean analyze = tagsFacade.analyze(analyzeRequest.getUrl(), analyzeRequest.getText());
            log.info("Result of analyze: [{}]", analyze);
            return new ResponseEntity<>(analyze, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error during analyze: [{}]", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllTags")
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(tagsFacade.getTags());
    }

    @GetMapping("/getTagsByUrl")
    public ResponseEntity<List<Tag>> getTagsByUrl(@RequestBody GetTagsRequest getTagsRequest) {
        log.info("GetTagsRequest [{}]", getTagsRequest.toString());
        return ResponseEntity.ok(tagsFacade.getTagsForUrl(getTagsRequest.getUrl()));
    }

}
