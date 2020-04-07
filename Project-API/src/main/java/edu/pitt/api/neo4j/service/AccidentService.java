package edu.pitt.api.neo4j.service;

import edu.pitt.api.neo4j.controller.AccidentController;
import edu.pitt.api.neo4j.domain.Accident;
import edu.pitt.api.neo4j.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class AccidentService {
    @Autowired
    AccidentRepository accidentRepository;

    public Collection<AccidentController.Count> countByState(){
        return accidentRepository.accidentCountByState();
    }

    public Collection<AccidentController.Count> countByCounty(String state){
        return accidentRepository.countByCounty(state);
    }

    public Collection<Accident> getAccidentByRoad(String state, String city, String street){
        return accidentRepository.getAccidentsByRoad(state,city,street);
    }

    public Collection<AccidentController.CountVisibility> countByvisibility(){
        return accidentRepository.countByVisibility();
    }

    public Collection<AccidentController.CountHumidity> countByHumidity(){
        return accidentRepository.countByHumidity();
    }

    public Collection<AccidentController.CountWeatherCondition> countByWeatherCondition(){
        return accidentRepository.countByWeatherCondition();
    }

}
