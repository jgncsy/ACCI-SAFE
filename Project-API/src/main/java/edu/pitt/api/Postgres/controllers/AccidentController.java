package edu.pitt.api.Postgres.controllers;

import edu.pitt.api.Postgres.config.AppKeys;
import edu.pitt.api.Postgres.models.Accidents;
import edu.pitt.api.Postgres.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(AppKeys.Postgres_API_PATH + "/accident")
public class AccidentController {

    @Autowired
    AccidentRepository accidentRepository;

    @GetMapping(value = "/numbersByState")
    public List<Object> getNumbersByState(){
        return accidentRepository.countByState();
    }

    @GetMapping(value = "/numbersByCounty/{state}")
    public List<Object> getNumbersByCounty(@PathVariable String state){
        return accidentRepository.countByCounty(state);
    }

    @GetMapping(value = "/accidentsByRoad/{state}/{city}/{road}")
    public List<Accidents> getAccidentsByRoad(@PathVariable String state, @PathVariable String city, @PathVariable String road) {
        return accidentRepository.getAccidentsByRoad(state,city,"%"+road+"%");
    }

    @GetMapping(value = "/numbersByVisibility")
    public List<Object> getNumbersByVisibility() {
        return accidentRepository.countByVisibility();
    }

    @GetMapping(value = "/numbersByHumidity")
    public List<Object> getNumbersByHumidity() {
        return accidentRepository.countByHumidity();
    }

    @GetMapping(value = "/numbersByWeatherCondition")
    public List<Object> getNumbersByWeatherCondition() {
        return accidentRepository.countByWeatherCondition();
    }

    public class count {
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
}
