package askryggdrasillsapi.askrserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AskrServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AskrServerApplication.class, args);
    }


    @RequestMapping("/")
    String index(){
        return "Hello Spring Boot";
    }

}
