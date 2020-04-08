package edu.pitt.api.Postgres.repository;

import com.sun.javafx.collections.MappingChange;
import edu.pitt.api.Postgres.controllers.AccidentController;
import edu.pitt.api.Postgres.models.Accidents;
import edu.pitt.api.neo4j.controller.Neo4jAccidentController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AccidentRepository extends JpaRepository<Accidents, Long> {
    List<Accidents> findAllBySource(String username);

    Accidents findOneById(Long reportId);

    @Query(value = "select * from Accidents a order by a.starttime limit 100", nativeQuery = true)
    List<Accidents> findFirst100OrderByStartTimeDesc();

    @Query(value = "select a.state as location, count(a) as number from Accidents a group by a.state")
    List<AccidentController.CountImp> countByState();

    @Query("select a.county as location, count(a) as number from Accidents a where a.state=:state group by a.county")
    List<AccidentController.CountImp> countByCounty(@Param("state") String state);

    @Query("select a from Accidents a where a.state =:state and a.city=:city and a.street like :street")
    List<Accidents> getAccidentsByRoad(@Param("state") String state,@Param("city") String city,@Param("street") String street);

    @Query("select a.visibility as visibility , count(a) as number from Accidents a group by a.visibility")
    List<AccidentController.CountVisibilityImp> countByVisibility();


    @Query("select a.humidity as humidity, count(a) as number from Accidents a group by a.humidity")
    List<AccidentController.CountHumidityImp> countByHumidity();


    @Query("select a.weathercondition as weathercondition, count(a) as number from Accidents a group by a.weathercondition")
    List<AccidentController.CountWeatherConditionImp> countByWeatherCondition();
}
