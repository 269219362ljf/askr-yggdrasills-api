package askryggdrasillsapi.askrserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Neo4jTest extends AskrServerApplicationTests {

    Logger logger = LoggerFactory.getLogger(Neo4jTest.class);

//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void createUserNode(){
//        System.out.println(userRepository);
//        UserNode userNode = new UserNode();
//        userNode.setName("老鼠");
//        userNode.setUserId("123");
//        UserNode save = userRepository.save(userNode);
//        logger.info(save.toString());
//        Assert.assertTrue(save!=null);
//    }
//
//    @Test
//    public void delAll(){
//        userRepository.deleteAll();
//    }


}
