package pl.kamilpek.morfeusz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kamilpek.morfeusz.model.Tag;
import pl.kamilpek.morfeusz.repository.TagRepository;
import pl.kamilpek.morfeusz.service.AnalyzerService;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagsFacade {

    private final TagRepository tagRepository;
    private final AnalyzerService analyzerService;

    public boolean analyze(String url, String text) {
        return analyzerService.analyze(url, text);
    }

    public List<Tag> getTagsForUrl(String url) {
        List<Tag> tags = new ArrayList<>();
        Iterator<Tag> tagsIterator = tagRepository.findByUrls(url).iterator();
        while (tagsIterator.hasNext()) {
            Tag nextTag = tagsIterator.next();
            tags.add(nextTag);
        }
        log.info("Found [{}] tags", tags.size());
        return tags;
    }

    public List<Tag> getTags() {
        List<Tag> tags = tagRepository.findAll();
        log.info("Found [{}] tags", tags.size());
        return tags;
    }

}
