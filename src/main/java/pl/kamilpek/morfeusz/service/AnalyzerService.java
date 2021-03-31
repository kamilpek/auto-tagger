package pl.kamilpek.morfeusz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kamilpek.morfeusz.model.Tag;
import pl.kamilpek.morfeusz.repository.TagRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyzerService {

    private final TagRepository tagRepository;
    private final MorfeuszService morfeuszService;

    public boolean analyze(String url, String text) {
        List<String> analyzeResult = new ArrayList<>(morfeuszService.analyze(text));
        for (String tagName : analyzeResult) {
            Iterator<Tag> tagsIterator = tagRepository.findByTag(tagName).iterator();
            if(tagsIterator.hasNext()) {
                while (tagsIterator.hasNext()) {
                    Tag tag = tagsIterator.next();
                    List<String> urls = tag.getUrls();
                    urls.add(url);
                    tag.setUrls(urls);
                    tagRepository.save(tag);
                    log.info("Updated new tag: [{}]", tag.toString());
                }
            } else {
                Tag tag = new Tag();
                tag.setUrls(new ArrayList<>(Set.of(url)));
                tag.setTag(tagName);
                tagRepository.save(tag);
                log.info("Added new tag: [{}]", tag.toString());
            }
        }
        return true;
    }

}
