package askr.yaggdrasills.neo4j.repository;

import askr.yaggdrasills.neo4j.node.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserNode,Long> {

}
