package edu.pitt.api.neo4j.repository;

import edu.pitt.api.neo4j.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
    public interface UserRepository extends Neo4jRepository<User, Long> {

        User findOneByUsername(String username);

        Optional<User> findOneByUsernameAndPassword(String username, String password);

        User findOneByUsernameAndCityAndStateAndEmailAndPhonenumber(String username, String city, String state, String email, String phonenumber);

        User findOneByEmail(String email);

        void deleteByUsername(String username);
    }