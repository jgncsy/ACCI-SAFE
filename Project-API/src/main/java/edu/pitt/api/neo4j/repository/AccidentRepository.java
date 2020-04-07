package edu.pitt.api.neo4j.repository;

import edu.pitt.api.neo4j.controller.AccidentController;
import edu.pitt.api.neo4j.domain.Accident;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface AccidentRepository extends Neo4jRepository<Accident,Long> {

    List<Accident> findAllBySource(String username);

    Accident findOneById(Long reportId);

    @Query("match (p) return p order by p.starttime limit 100")
    List<Accident> findFirst100OrderByStartTimeDesc();

    @Query("match (p) return p.state AS location, count(*) AS number")
    Collection<AccidentController.Count> accidentCountByState();

    @Query("match (p:Accident{state:$state}) return p.county AS location, count(*) AS number")
    Collection<AccidentController.Count> countByCounty(@Param("state") String state);

    @Query("match (p) where p.city= $city and p.state=$state and p.street =$street return p")
    Collection<Accident> getAccidentsByRoad(@Param("state") String state,@Param("city") String city,@Param("street") String street);

    @Query("match(p) return p.visibility AS visibility,count(*) AS number")
    Collection<AccidentController.CountVisibility> countByVisibility();


    @Query("match(p) return p.humidity AS humidity, count(*) AS number")
    Collection<AccidentController.CountHumidity> countByHumidity();


    @Query("match(p) return p.weatherCondition AS weatherCondition, count(*) AS number")
    Collection<AccidentController.CountWeatherCondition> countByWeatherCondition();
}
