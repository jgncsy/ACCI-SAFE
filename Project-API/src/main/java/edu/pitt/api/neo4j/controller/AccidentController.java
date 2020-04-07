package edu.pitt.api.neo4j.controller;


import edu.pitt.api.neo4j.Config.AppKeys;
import edu.pitt.api.neo4j.domain.Accident;
import edu.pitt.api.neo4j.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@RestController
@RequestMapping(AppKeys.NEO4J_API_PATH + "/accident")
@CrossOrigin
public class AccidentController {
    @Autowired
    AccidentService accidentService;

    @GetMapping("/numbersByState")
    public ResponseEntity<?> getNumbersByState(){
        Collection<Count> results = accidentService.countByState();
        System.out.println(results);
        return new ResponseEntity<Collection<Count>>(results,HttpStatus.OK);
    }



    @GetMapping(value = "/numbersByCounty/{state}")
    public ResponseEntity<?> getNumbersByCounty(@PathVariable String state){
        Collection<Count> results = accidentService.countByCounty(state);
        return new ResponseEntity<Collection<Count>>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/accidentsByRoad/{state}/{city}/{road}")
    public ResponseEntity<?> getAccidentsByRoad(@PathVariable String state, @PathVariable String city, @PathVariable String road) {

        Collection<Accident> results = accidentService.getAccidentByRoad(state,city,road);
        return new ResponseEntity<Collection<Accident>>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/numbersByVisibility")
    public ResponseEntity<?> getNumbersByVisibility(){
        Collection<CountVisibility> results = accidentService.countByvisibility();
        return new ResponseEntity<Collection<CountVisibility>>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/numbersByHumidity")
    public ResponseEntity<?> getNumbersByHumidity(){
        Collection<CountHumidity> results = accidentService.countByHumidity();
        return new ResponseEntity<Collection<CountHumidity>>(results,HttpStatus.OK);
    }

    @GetMapping(value = "/numbersByWeatherCondition")
    public ResponseEntity<?> getNumbersByWeatherCondition(){
        Collection<CountWeatherCondition> results = accidentService.countByWeatherCondition();
        return new ResponseEntity<Collection<CountWeatherCondition>>(results,HttpStatus.OK);
    }



    @QueryResult
    public class Count {
        String location;
        int number;

        public String getLocation(){
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    @QueryResult
    public class CountVisibility {
        float visibility;
        int number;

        public float getVisibility() {
            return visibility;
        }

        public void setVisibility(float visibility) {
            this.visibility = visibility;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    @QueryResult
    public class CountHumidity {
        float humidity;
        int number;

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    @QueryResult
    public class CountWeatherCondition {
        String weatherCondition;
        int number;

        public String getWeatherCondition() {
            return weatherCondition;
        }

        public void setWeatherCondition(String weatherCondition) {
            this.weatherCondition = weatherCondition;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }





}
