package edu.pitt.api.neo4j.repository;

import edu.pitt.api.neo4j.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends Neo4jRepository<User, Long> {

    User findOneByUsernameAndPassword(String username, String password);

}
