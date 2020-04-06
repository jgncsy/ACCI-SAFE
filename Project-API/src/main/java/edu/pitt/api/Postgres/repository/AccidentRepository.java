package edu.pitt.api.Postgres.repository;

import edu.pitt.api.Postgres.controllers.AccidentController;
import edu.pitt.api.Postgres.models.Accidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccidentRepository extends JpaRepository<Accidents, Long> {
    List<Accidents> findAllBySource(String username);

    Accidents findOneById(Long reportId);

    @Query(value = "select a from Accidents a group by a.starttime order by a.starttime limit 100", nativeQuery = true)
    List<Accidents> findFirst100OrderByStartTimeDesc();

    @Query("select a.state, count(a) from Accidents a group by a.state")
    List<AccidentController.count> countByState();

    @Query("select a.county, count(a) from Accidents a where a.state=:state group by a.county")
    List<AccidentController.count> countByCounty(@Param("state") String state);

    @Query("select a from Accidents a where a.state =:state and a.city=:city and a.street like :street")
    List<Accidents> getAccidentsByRoad(@Param("state") String state,@Param("city") String city,@Param("street") String street);

    @Query("select a.visibility, count(a) from Accidents a group by a.visibility")
    List<AccidentController.count> countByVisibility();


    @Query("select a.humidity, count(a) from Accidents a group by a.humidity")
    List<AccidentController.count> countByHumidity();


    @Query("select a.weathercondition, count(a) from Accidents a group by a.weathercondition")
    List<AccidentController.count> countByWeatherCondition();
}
