package edu.pitt.api.Postgres.controllers;

import edu.pitt.api.Postgres.config.AppKeys;
import edu.pitt.api.Postgres.models.Accidents;
import edu.pitt.api.Postgres.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<RoadLocationImp> getAccidentsByRoad(@PathVariable String state, @PathVariable String city, @PathVariable String road) {
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
        String id;
        int value;

        public void setId(String id) {
            this.id = id;
        }

        public int getValue() {
            return value;
        }

        public String getId() {
            return id;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public interface CountImp {
        void setId(String id);
        int getValue();
        String getId();
        void setValue(int value);
    }

    @QueryResult
    public static class CountVisibility implements CountVisibilityImp  {
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

    @QueryResult
    public class RoadLocation {
        float latitude;
        float longitude;

        public void setLongitude(String longitude) {
            if (longitude == null) return;
            this.longitude = Float.parseFloat(longitude);
        }

        public void setLatitude(String latitude) {
            if (latitude == null) return;
            this.latitude = Float.parseFloat(latitude);
        }

        public float getLatitude() {
            return latitude;
        }

        public float getLongitude() {
            return longitude;
        }
    }

    public interface RoadLocationImp {
        float getLatitude();
        float getLongitude();
        void setLatitude(String latitude);
        void setLongitude(String longitude);
    }

}
