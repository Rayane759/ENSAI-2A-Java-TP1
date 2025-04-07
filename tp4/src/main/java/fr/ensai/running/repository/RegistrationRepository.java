package fr.ensai.running.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.model.Competition;
import fr.ensai.running.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findByAthleteAndCompetition(Athlete athlete, Competition competition);

    @Query("SELECT r.athlete.idAthlete FROM Registration r WHERE r.competition.idCompetition = :idCompetition")
    List<Long> findAthleteIdByCompetitionId(Long idCompetition);
}
