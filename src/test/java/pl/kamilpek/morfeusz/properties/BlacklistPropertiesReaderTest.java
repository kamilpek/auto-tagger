package pl.kamilpek.morfeusz.properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BlacklistPropertiesReaderTest {
    @InjectMocks
    BlacklistPropertiesReader blacklistPropertiesReader;

    @Test
    public void testGetBlacklist() throws Exception {
        List<String> result = blacklistPropertiesReader.getBlacklist();
        Assert.assertEquals(Arrays.asList("mieszkaniec","czytelnik","ąęśćź"), result);
    }
}
