package fr.ensai.running.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.model.Competition;
import fr.ensai.running.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findByAthleteAndCompetition(Athlete athlete, Competition competition);

    @Query("SELECT r.athlete.id FROM Registration r WHERE r.competition.id = :idCompetition")
    List<Long> findAthleteIdByCompetitionId(@Param("idCompetition") Long idCompetition);

}
