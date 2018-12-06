package askryggdrasillsapi.askrserver;

import askr.yaggdrasills.neo4j.node.UserNode;
import askr.yaggdrasills.neo4j.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Neo4jTest extends AskrServerApplicationTests {

    Logger logger = LoggerFactory.getLogger(Neo4jTest.class);

    @Resource
    UserRepository userRepository;

    @Autowired
    org.neo4j.ogm.config.Configuration configuration;

    @Test
    public void createUserNode(){
        System.out.println(userRepository);
        UserNode userNode = new UserNode();
        userNode.setName("老鼠");
        userNode.setUserId("123");
        UserNode save = userRepository.save(userNode);
        logger.info(save.toString());
        Assert.assertTrue(save!=null);
    }

    @Test
    public void delAll(){
        userRepository.deleteAll();
    }

    @Test
    public void test(){
        System.out.println("success");
    }

    @Test
    public void showConfiguration(){
        //查看neo4j的驱动和uri
        System.out.println("neo4j drivername:"+configuration.getDriverClassName());
        System.out.println("neo4j uri:"+configuration.getURI());
    }






}
