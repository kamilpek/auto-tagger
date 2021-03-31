package pl.kamilpek.morfeusz.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class BlacklistPropertiesReader {

    public List<String> getBlacklist() {
        Properties properties = loadProperties();
        if(properties == null) return Collections.emptyList();
        String[] blacklists = properties.getProperty("blacklist").split(";");
        return Arrays.asList(blacklists);
    }

    private Properties loadProperties() {
        Resource resource = new ClassPathResource("/blacklist.properties");
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            log.error("Error during opening blacklist properties file: {}", e.getMessage());
        }
        return null;
    }

}
