package pl.kamilpek.morfeusz.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kamilpek.morfeusz.model.Tag;

import java.util.Iterator;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;

    @Test
    public void testFindByUrls(){
        Iterable<Tag> byUrls = tagRepository.findByUrls("test.pl");
        Iterator<Tag> tagIterator = byUrls.iterator();
        assertThat(tagIterator.hasNext()).isTrue();
    }
}
