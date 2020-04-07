package edu.pitt.api.neo4j.controller;


import edu.pitt.api.neo4j.Config.AppKeys;
import edu.pitt.api.neo4j.domain.Neo4jAccident;
import edu.pitt.api.neo4j.service.Neo4jAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@RestController
@RequestMapping(AppKeys.NEO4J_API_PATH + "/accident")
@CrossOrigin
public class Neo4jAccidentController {
    @Autowired
    Neo4jAccidentService neo4jAccidentService;

    @GetMapping("/numbersByState")
    public ResponseEntity<?> getNumbersByState(){
        Collection<Object> results = neo4jAccidentService.countByState();
        System.out.println(results);
        return new ResponseEntity<Object>(results,HttpStatus.OK);
    }



    @GetMapping(value = "/numbersByCounty/{state}")
    public ResponseEntity<?> getNumbersByCounty(@PathVariable String state){
        Collection<Object> results = neo4jAccidentService.countByCounty(state);
        return new ResponseEntity<Object>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/accidentsByRoad/{state}/{city}/{road}")
    public ResponseEntity<?> getAccidentsByRoad(@PathVariable String state, @PathVariable String city, @PathVariable String road) {

        Collection<Neo4jAccident> results = neo4jAccidentService.getAccidentByRoad(state,city,road);
        return new ResponseEntity<Object>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/numbersByVisibility")
    public ResponseEntity<?> getNumbersByVisibility(){
        Collection<Object> results = neo4jAccidentService.countByvisibility();
        return new ResponseEntity<Object>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/numbersByHumidity")
    public ResponseEntity<?> getNumbersByHumidity(){
        Collection<Object> results = neo4jAccidentService.countByHumidity();
        return new ResponseEntity<Object>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/numbersByWeatherCondition")
    public ResponseEntity<?> getNumbersByWeatherCondition(){
        Collection<Object> results = neo4jAccidentService.countByWeatherCondition();
        return new ResponseEntity<Object>(results,HttpStatus.OK);
    }



//    @QueryResult
//    public class Count {
//        String location;
//        int number;
//
//        public String getLocation(){
//            return location;
//        }
//
//        public void setLocation(String location) {
//            this.location = location;
//        }
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//    }
//
//    @QueryResult
//    public class CountVisibility {
//        float visibility;
//        int number;
//
//        public float getVisibility() {
//            return visibility;
//        }
//
//        public void setVisibility(float visibility) {
//            this.visibility = visibility;
//        }
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//    }
//
//    @QueryResult
//    public class CountHumidity {
//        float humidity;
//        int number;
//
//        public float getHumidity() {
//            return humidity;
//        }
//
//        public void setHumidity(float humidity) {
//            this.humidity = humidity;
//        }
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//    }
//
//    @QueryResult
//    public class CountWeatherCondition {
//        String weatherCondition;
//        int number;
//
//        public String getWeatherCondition() {
//            return weatherCondition;
//        }
//
//        public void setWeatherCondition(String weatherCondition) {
//            this.weatherCondition = weatherCondition;
//        }
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//    }





}
