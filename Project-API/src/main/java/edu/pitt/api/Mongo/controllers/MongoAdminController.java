package edu.pitt.api.Mongo.controllers;

import edu.pitt.api.Mongo.config.AppKeys;
import edu.pitt.api.Mongo.models.MongoUsers;
import edu.pitt.api.Mongo.repository.MongoAccidentsRepository;
import edu.pitt.api.Mongo.repository.MongoUsersRepository;
import edu.pitt.api.Postgres.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

//import edu.pitt.api.Mongo.repository.AdminRepository;


@CrossOrigin
@RestController
@RequestMapping(AppKeys.Mongo_API_PATH + "/admin")
public class MongoAdminController {
    @Autowired
    MongoUsersRepository userRepository;
//    @Autowired
//    AdminRepository adminRepository;
    @Autowired
MongoAccidentsRepository accidentRepository;

    @Autowired
    private JwtTokenProvider MongojwtTokenProvider;

    static Object getObject(Optional<MongoUsers> admin, JwtTokenProvider jwtTokenProvider) {
        try {
            String token = jwtTokenProvider.createMongoToken(admin.get());
            HashMap<String, Object> result = new HashMap<>();
            result.put("User", admin);
            result.put("token", token);
            System.out.println(token);
            return result;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username/password supplied");
        }
    }


}