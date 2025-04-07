package fr.ensai.running.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.model.Competition;
import fr.ensai.running.model.Registration;
import fr.ensai.running.repository.AthleteRepository;
import fr.ensai.running.repository.CompetitionRepository;
import fr.ensai.running.repository.RegistrationRepository;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    public void deleteCompetition(Long id) {
        registrationRepository.findAll().stream()
                .filter(r -> r.getCompetition().getIdCompetition().equals(id))
                .forEach(r -> registrationRepository.delete(r));

        competitionRepository.deleteById(id);
    }

    public List<Athlete> findRegisteredAthletes(Long idCompetition) {
        List<Long> athleteIds = registrationRepository.findAthleteIdByCompetitionId(idCompetition);
        return athleteRepository.findAllById(athleteIds);
    }

    public void deleteRegistration(Long idCompetition, Long idAthlete) {
        Optional<Competition> comp = competitionRepository.findById(idCompetition);
        Optional<Athlete> ath = athleteRepository.findById(idAthlete);

        if (comp.isPresent() && ath.isPresent()) {
            Registration reg = registrationRepository.findByAthleteAndCompetition(ath.get(), comp.get());
            if (reg != null) {
                registrationRepository.delete(reg);
            }
        }
    }
}
