package edu.pitt.api.neo4j.controller;


import edu.pitt.api.neo4j.Config.AppKeys;
import edu.pitt.api.neo4j.domain.Neo4jAccident;
import edu.pitt.api.neo4j.service.Neo4jAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(AppKeys.NEO4J_API_PATH + "/accident")
@CrossOrigin
public class Neo4jAccidentController {
    @Autowired
    Neo4jAccidentService neo4jAccidentService;

    @GetMapping("/numbersByState")
    public List<Count> getNumbersByState(){
        return neo4jAccidentService.countByState();
    }



    @GetMapping(value = "/numbersByCounty/{state}")
    public List<Count> getNumbersByCounty(@PathVariable String state){
        return neo4jAccidentService.countByCounty(state);
    }

    @GetMapping(value = "/accidentsByRoad/{state}/{city}/{road}")
    public List<Neo4jAccident> getAccidentsByRoad(@PathVariable String state, @PathVariable String city, @PathVariable String road) {

        return neo4jAccidentService.getAccidentByRoad(state,city,road);
    }

    @GetMapping(value = "/numbersByVisibility")
    public List<CountVisibility> getNumbersByVisibility(){
        return neo4jAccidentService.countByvisibility();
    }

    @GetMapping(value = "/numbersByHumidity")
    public List<CountHumidity> getNumbersByHumidity(){
        return neo4jAccidentService.countByHumidity();
    }

    @GetMapping(value = "/numbersByWeatherCondition")
    public List<CountWeatherCondition> getNumbersByWeatherCondition(){
        return neo4jAccidentService.countByWeatherCondition();
    }



    @QueryResult
    public static class Count {
        String location;
        int number;

        public int getNumber() {
            return number;
        }

        public String getLocation() {
            return location;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    @QueryResult
    public class CountVisibility {
        Double visibility;
        int number;

        public Double getVisibility() {
            return visibility;
        }

        public void setVisibility(Double visibility) {
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
    public  class CountHumidity {
        Double humidity;
        int number;

        public Double getHumidity() {
            return humidity;
        }

        public void setHumidity(Double humidity) {
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
