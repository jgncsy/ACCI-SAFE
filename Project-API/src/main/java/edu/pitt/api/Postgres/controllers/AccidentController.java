package edu.pitt.api.Postgres.controllers;

import edu.pitt.api.Postgres.config.AppKeys;
import edu.pitt.api.Postgres.models.Accidents;
import edu.pitt.api.Postgres.repository.AccidentRepository;
import edu.pitt.api.neo4j.controller.Neo4jAccidentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(AppKeys.Postgres_API_PATH + "/accident")
public class AccidentController {

    @Autowired
    AccidentRepository accidentRepository;

    @GetMapping(value = "/numbersByState")
    public List<CountImp> getNumbersByState(){
        return accidentRepository.countByState();
    }

    @GetMapping(value = "/numbersByCounty/{state}")
    public List<CountImp> getNumbersByCounty(@PathVariable String state){
        return accidentRepository.countByCounty(state);
    }

    @GetMapping(value = "/accidentsByRoad/{state}/{city}/{road}")
    public List<Accidents> getAccidentsByRoad(@PathVariable String state, @PathVariable String city, @PathVariable String road) {
        return accidentRepository.getAccidentsByRoad(state,city,"%"+road+"%");
    }

    @GetMapping(value = "/numbersByVisibility")
    public List<CountVisibilityImp> getNumbersByVisibility() {
        return accidentRepository.countByVisibility();
    }

    @GetMapping(value = "/numbersByHumidity")
    public List<AccidentController.CountHumidityImp> getNumbersByHumidity() {
        return accidentRepository.countByHumidity();
    }

    @GetMapping(value = "/numbersByWeatherCondition")
    public List<AccidentController.CountWeatherConditionImp> getNumbersByWeatherCondition() {
        return accidentRepository.countByWeatherCondition();
    }


    @QueryResult
    public class Count implements CountImp {
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

    public interface CountImp {
        String getLocation();
        void setNumber(int number);
        void setLocation(String location);
        int getNumber();
    }

    @QueryResult
    public class CountVisibility implements CountVisibilityImp  {
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

    public interface CountVisibilityImp {
        Double getVisibility();
        void setVisibility(Double visibility);
        int getNumber();
        void setNumber(int number);

    }

    @QueryResult
    public  class CountHumidity implements CountHumidityImp {
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

    public interface CountHumidityImp {
        Double getHumidity();
        void setHumidity(Double humidity);
        int getNumber();
        void setNumber(int number);
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

    public interface CountWeatherConditionImp {
        String getWeatherCondition();
        void setWeatherCondition(String weatherCondition);
        int getNumber();
        void setNumber(int number);
    }

}
