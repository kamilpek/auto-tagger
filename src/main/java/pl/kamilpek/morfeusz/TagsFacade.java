package pl.kamilpek.morfeusz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kamilpek.morfeusz.model.Tag;
import pl.kamilpek.morfeusz.repository.TagRepository;
import pl.kamilpek.morfeusz.service.AnalyzerService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagsFacade {

    private final TagRepository tagRepository;
    private final AnalyzerService analyzerService;

    public boolean analyze(String url, String text) {
        return analyzerService.analyze(url, text);
    }

    public List<String> getTagsForUrl(String url) {
        List<String> tags = new ArrayList<>();
        Iterator<Tag> tagsIterator = tagRepository.findByUrls(url).iterator();
        while (tagsIterator.hasNext()) {
            Tag nextTag = tagsIterator.next();
            tags.add(nextTag.getTag());
        }
        log.info("Found [{}] tags", tags.size());
        return tags;
    }

    public List<Tag> getTags() {
        List<Tag> tags = tagRepository.findAll();
        log.info("Found [{}] tags", tags.size());
        return tags;
    }

    public List<String> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(Tag::getTag).collect(Collectors.toList());
    }

    public List<String> getUrlsForTag(String tag) {
        List<String> urls = new ArrayList<>();
        Iterator<Tag> iterator = tagRepository.findByTag(tag).iterator();
        while (iterator.hasNext()) {
            urls.addAll(iterator.next().getUrls());
        }
        log.info("Found [{}] urls for tag [{}]", urls.size(), tag);
        return urls;
    }

}
