package askr.yaggdrasills;

import askr.yaggdrasills.model.TestModel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@EnableNeo4jRepositories
@ComponentScan(basePackages = {"askr.yaggdrasills", "askr.yaggdrasills.neo4j","askr.midgard"})
@MapperScan(basePackages = {"askr.mysql.mapper"})
@SpringBootApplication
public class AskrServerApplication {

    @Value("${test.value}")
    private String testValue;

    @Autowired
    private TestModel testModel;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AskrServerApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


    @RequestMapping("/")
    String index() {
        String result = "Hello Spring Boot\n";
        result += testValue + "\n";
        result += testModel.toString();
        return "Hello Spring Boot " + result;
    }

}
