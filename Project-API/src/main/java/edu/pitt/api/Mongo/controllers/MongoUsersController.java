package edu.pitt.api.Mongo.controllers;

import edu.pitt.api.Mongo.config.AppKeys;
import edu.pitt.api.Mongo.models.MongoAccidents;
import edu.pitt.api.Mongo.models.MongoUsers;
import edu.pitt.api.Mongo.repository.MongoAccidentsRepository;
import edu.pitt.api.Mongo.repository.MongoUsersRepository;
import edu.pitt.api.Postgres.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(AppKeys.Mongo_API_PATH + "/user")
public class MongoUsersController {
    @Autowired
    private MongoUsersRepository userRepo;
    @Autowired
    private MongoAccidentsRepository acidRepo;
    @Autowired
    private JwtTokenProvider MongojwtTokenProvider;

    /*User registration*/
    @PostMapping(value = "/signup")
    public Object signup(@RequestBody MongoUsers user) {
        MongoUsers u = userRepo.findUsersByUsrnameIs(user.getUsrname());
        MongoUsers EmailUser = userRepo.findUsersByEmailIs(user.getEmail());
        if (u != null) {
            return ResponseEntity.badRequest().body("username already exists");
//            throw new RuntimeException("username already exists");
        } else if (EmailUser != null) {
            throw new RuntimeException("email already exists");
        } else {
            String token = MongojwtTokenProvider.createMongoToken(user);

            HashMap<String, Object> result = new HashMap<>();
            result.put("user", userRepo.save(user));
            result.put("token", token);
            return result;
        }
    }

    /*Check info is matched or not*/
    @GetMapping(value = "/infoCheck")
    public MongoUsers checkInfoBy5Fields(@RequestBody MongoUsers user) {

        List<MongoUsers> u= userRepo.checkInfo(user.getUsrname(), user.getCity(),
                user.getState(), user.getEmail(), user.getPhone());
        if(u.size()==0){
            throw new RuntimeException("user doesn't exist");
        }else if(u.size()>1){
            throw new RuntimeException("user duplicated");
        }else return u.get(0);

    }

    /*Reset password for user*/
    @PutMapping(value = "/updatePassword/{username}")
    public MongoUsers restPassword(@PathVariable String username, @RequestBody LoginBody body) {
        MongoUsers u = userRepo.findUsersByUsrnameIs(username);
        if (u != null) {
            u.setPwd(body.password);
            return userRepo.save(u);
        } else throw new RuntimeException("username doesn't exist (Mongodb)");

    }

    /*user login*/
    @PostMapping(value = "/login")
    public Object login(@RequestBody LoginBody body) {
        MongoUsers u = userRepo.findUsersByUsrnameIsAndPwdIs(body.username, body.password);
        if (u == null) {
            return ResponseEntity.badRequest().body("User username and password mismatch");
        }
        if (u.isAdmin()) {
            return ResponseEntity.badRequest().body("You are Admin Please Use Admin Login Link");
        }
        return MongoAdminController.getObject(Optional.of(u), MongojwtTokenProvider);
    }


    /*Get all user info*/
    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public MongoUsers getAllInfo(@PathVariable String username) {
        MongoUsers user = userRepo.findUsersByUsrnameIs(username);
        if (user == null) {
            throw new RuntimeException("username doesn't exist");
        } else {
            return user;
        }
    }


    /*update new info for user*/
    @PutMapping(value = "/updateAllInfo/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public MongoUsers updateAllInfo(@PathVariable String username, @RequestBody MongoUsers user) {
        MongoUsers u = userRepo.findUsersByUsrnameIs(username);
        if (u == null) {
            throw new RuntimeException("username doesn't exist");
        } else {

            //check email eligibility
            String newEmail = user.getEmail();
            if (!u.getEmail().equalsIgnoreCase(newEmail)) {
                if (userRepo.findUsersByEmailIs(newEmail) != null) {
                    throw new RuntimeException("email already exist");
                } else {
                    u.setEmail(newEmail);
                }
            }
            u.setCity(user.getCity());
            u.setState(user.getState());
            u.setPhone(user.getPhone());
            return userRepo.save(u);

        }
    }

    /*Update report settings*/
    @PutMapping(value = "/updateSettings/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public MongoUsers updateSettings(@PathVariable String username) {
        MongoUsers u = userRepo.findUsersByUsrnameIs(username);
        if (u == null) {
            throw new RuntimeException("username doesn't exist");
        } else {
            u.setAnonymous(!u.isAnonymous());
        }
        return userRepo.save(u);
    }

    /*Self-report mongoAccidents*/
    @PostMapping(value = "/self-report/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public MongoAccidents self_report(@PathVariable String username, @RequestBody MongoAccidents mongoAccidents) {
        MongoUsers user = userRepo.findUsersByUsrnameIs(username);
        if (user == null) {
            throw new RuntimeException("username doesn't exist");
        } else {
            if (!user.isAnonymous()) {
                mongoAccidents.setSource(username);
            }
        }
        return acidRepo.save(mongoAccidents);
    }

    /*View user's all history reports*/
    @GetMapping(value = "/reports/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<MongoAccidents> reportsByUsername(@PathVariable String username) {
        return acidRepo.findAllBySource(username);
    }


    /* Delete pending reports */
    @DeleteMapping(value = "/{username}/{reportId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void deleteByReportId (@PathVariable String username, @PathVariable Long reportId) {
        try{
            acidRepo.deleteById(String.valueOf(reportId));
        } catch (NullPointerException er) {
            throw new RuntimeException("cannot find report");
        }

    }

    public static class LoginBody {
        public String username;
        public String password;
    }

    @GetMapping(value = "/All/{username}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<MongoUsers> getAllUser(@PathVariable String username) {
//        return userRepo.findAll();
        if(username.equals("All")) return userRepo.findAll();
        return userRepo.findUsersByUsrname(username);
    }

    @GetMapping(value = "/One/{id}")
    public List<MongoUsers> getOneUser(@PathVariable String id) {
        return userRepo.findByThePersonsid(id);
    }

}
