package askr.yaggdrasills.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "askr.yaggdrasills.neo4j.repository")
@EnableTransactionManagement
public class Neo4jConfiguration {







}
