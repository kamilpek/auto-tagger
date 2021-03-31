package pl.kamilpek.morfeusz.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kamilpek.morfeusz.properties.BlacklistPropertiesReader;

import java.util.Arrays;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MorfeuszServiceTest {
    @Mock
    BlacklistPropertiesReader blacklistPropertiesReader;
    @InjectMocks
    MorfeuszService morfeuszService;

    @Test
    public void testAnalyze() throws Exception {
        when(blacklistPropertiesReader.getBlacklist()).thenReturn(Arrays.asList("ala"));
        Set<String> result = morfeuszService.analyze("ala ma kota");
        Assert.assertFalse(result.isEmpty());
    }
}
