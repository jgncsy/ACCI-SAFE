package edu.pitt.api.Mongo.repository;

import edu.pitt.api.Mongo.models.MongoUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MongoUsersRepository extends MongoRepository<MongoUsers,String > {

    @Query(value="{ '_id' : ?0 }", fields="{ 'name' : 1, 'email' : 1}")
    List<MongoUsers> findByThePersonsid(String id);


    @Query(value = "{'usrname':?0,'city':?1,'state':?2,'email':?3,'phone':?4}")
    List<MongoUsers> checkInfo(String usrname, String city, String state, String email, String phone);


    List<MongoUsers> findOneByUsrname(String usrname);

    List<MongoUsers> findUsersByUsrname(String usrname);

    MongoUsers findUsersByUsrnameIs(String usrname);

    MongoUsers findUsersByEmailIs(String email);

    MongoUsers findUsersByUsrnameIsAndPwdIs(String usrname, String pwd);


}
