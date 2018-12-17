package askr.yaggdrasills.server.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "testmodel")
public class TestModel {

    private String id;
    private String name;


}
