package askr.yaggdrasills;

import askr.yaggdrasills.server.model.TestModel;
import askr.yaggdrasills.neo4j.node.UserNode;
import askr.yaggdrasills.neo4j.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@EnableNeo4jRepositories
@SpringBootApplication
public class AskrServerApplication {

    private static Logger log = LoggerFactory.getLogger(AskrServerApplication.class);
    @Resource
    UserRepository userRepository;
    @Autowired
    org.neo4j.ogm.config.Configuration configuration;
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


        System.out.println(userRepository);
        UserNode userNode = new UserNode();
        userNode.setName("老鼠");
        userNode.setUserId("123");
        UserNode save = userRepository.save(userNode);
        System.out.println(save.toString());

        return "Hello Spring Boot " + result;
    }

}
