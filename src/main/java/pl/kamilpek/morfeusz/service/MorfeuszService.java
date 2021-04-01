package pl.kamilpek.morfeusz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamilpek.morfeusz.properties.BlacklistPropertiesReader;
import pl.sgjp.morfeusz.Morfeusz;
import pl.sgjp.morfeusz.MorphInterpretation;
import pl.sgjp.morfeusz.ResultsIterator;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MorfeuszService {

    private final BlacklistPropertiesReader blacklistPropertiesReader;

    public Set<String> analyze(String text) {
        Set<String> articleTags = new TreeSet<>();
        Morfeusz instance = Morfeusz.createInstance();
        String string = filter(text.toLowerCase());
        ResultsIterator iterator = instance.analyseAsIterator(string);
        String orth = "";
        while(iterator.hasNext()) {
            MorphInterpretation morphInterpretation = iterator.next();
            String tag = morphInterpretation.getTag(instance);
            String lemma = morphInterpretation.getLemma();
            if(tag.contains("subst") && !orth.equals(morphInterpretation.getOrth()) && !lemma.contains(":")) {
                if(tag.contains("gen")) {
                    articleTags.add(lemma);
                    orth = morphInterpretation.getOrth();
                } else if (tag.contains("nom")) {
                    articleTags.add(lemma);
                    orth = morphInterpretation.getOrth();
                }
            }
        }
        System.out.println(Arrays.toString(articleTags.toArray()));
        return articleTags;
    }

    private String filter(String text) {
        List<String> blacklist = blacklistPropertiesReader.getBlacklist();
        return Arrays.stream(text.split(" "))
                .filter(it -> !blacklist.contains(it))
                .collect(Collectors.joining(" "));
    }

}
