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

    @Query(value = "select * from Accidents a order by a.starttime limit 100", nativeQuery = true)
    List<Accidents> findFirst100OrderByStartTimeDesc();

    @Query("select a.state as location, count(a) as number from Accidents a group by a.state")
    List<Object> countByState();

    @Query("select a.county, count(a) from Accidents a where a.state=:state group by a.county")
    List<Object> countByCounty(@Param("state") String state);

    @Query("select a from Accidents a where a.state =:state and a.city=:city and a.street like :street")
    List<Accidents> getAccidentsByRoad(@Param("state") String state,@Param("city") String city,@Param("street") String street);

    @Query("select a.visibility, count(a) from Accidents a group by a.visibility")
    List<Object> countByVisibility();


    @Query("select a.humidity, count(a) from Accidents a group by a.humidity")
    List<Object> countByHumidity();


    @Query("select a.weathercondition, count(a) from Accidents a group by a.weathercondition")
    List<Object> countByWeatherCondition();
}
