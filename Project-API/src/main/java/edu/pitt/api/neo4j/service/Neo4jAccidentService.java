package edu.pitt.api.neo4j.service;

import edu.pitt.api.neo4j.domain.Neo4jAccident;
import edu.pitt.api.neo4j.repository.Neo4jAccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class Neo4jAccidentService {
    @Autowired
    Neo4jAccidentRepository neo4jAccidentRepository;

    public Collection<Object> countByState(){
        return neo4jAccidentRepository.accidentCountByState();
    }

    public Collection<Object> countByCounty(String state){
        return neo4jAccidentRepository.countByCounty(state);
    }

    public Collection<Neo4jAccident> getAccidentByRoad(String state, String city, String street){
        return neo4jAccidentRepository.getAccidentsByRoad(state,city,street);
    }

    public Collection<Object> countByvisibility(){
        return neo4jAccidentRepository.countByVisibility();
    }

    public Collection<Object> countByHumidity(){
        return neo4jAccidentRepository.countByHumidity();
    }

    public Collection<Object> countByWeatherCondition(){
        return neo4jAccidentRepository.countByWeatherCondition();
    }

}
