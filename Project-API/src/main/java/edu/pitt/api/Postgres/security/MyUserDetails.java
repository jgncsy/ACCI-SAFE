package edu.pitt.api.Postgres.security;

import edu.pitt.api.Mongo.models.MongoUsers;
import edu.pitt.api.Mongo.repository.MongoUsersRepository;
import edu.pitt.api.Postgres.models.User;
import edu.pitt.api.Postgres.repository.UserRepository;
import edu.pitt.api.neo4j.domain.Neo4jUser;
import edu.pitt.api.neo4j.repository.Neo4jUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoUsersRepository mongoUsersRepository;
    @Autowired
    private Neo4jUserRepository neo4jUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findOneByUsername(username);
        final MongoUsers mongoUsers = mongoUsersRepository.findOneByUsername(username);
        final Neo4jUser neo4jUser = neo4jUserRepository.findOneByUsername(username);

        if (user != null) {
//            throw new UsernameNotFoundException("user '" + username + "' not found");
            return org.springframework.security.core.userdetails.User//
                    .withUsername(username)//
                    .password(user.getPassword())//
                    .authorities(user.getRoles())//
                    .accountExpired(false)//
                    .accountLocked(false)//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();
        }
        if(mongoUsers != null) {
            return org.springframework.security.core.userdetails.User//
                    .withUsername(username)//
                    .password(mongoUsers.getPassword())//
                    .authorities(mongoUsers.getRoles())//
                    .accountExpired(false)//
                    .accountLocked(false)//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();
        }
        if (neo4jUser != null) {
            return org.springframework.security.core.userdetails.User//
                    .withUsername(username)//
                    .password(neo4jUser.getPassword())//
                    .authorities(neo4jUser.getRoles())//
                    .accountExpired(false)//
                    .accountLocked(false)//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();
        }

        throw new UsernameNotFoundException("user '" + username + "' not found");
    }

}