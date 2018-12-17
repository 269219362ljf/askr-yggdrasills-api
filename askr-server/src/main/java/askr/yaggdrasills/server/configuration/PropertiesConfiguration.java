package askr.yaggdrasills.server.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class PropertiesConfiguration {
}
