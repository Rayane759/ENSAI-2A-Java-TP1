package fr.ensai.running.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.model.Registration;
import fr.ensai.running.repository.AthleteRepository;
import fr.ensai.running.repository.RegistrationRepository;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    /**
     * Find an Athlete by id
     * 
     * @return Athlete or null if not found
     */
    public Athlete findById(Long id) {
        return athleteRepository.findById(id).orElse(null);
    }

    /**
     * List of all Athletes
     */
    public List<Athlete> findAll() {
        return athleteRepository.findAll();
    }

    /**
     * Create or Update an Athlete
     */
    public Athlete save(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    /**
     * Delete an Athlete by id
     */
    public void deleteById(Long id) {
        List<Registration> regs = registrationRepository.findAll().stream()
                .filter(r -> r.getAthlete().getIdAthlete() == id)
                .collect(Collectors.toList());
        regs.forEach(registrationRepository::delete);
        athleteRepository.deleteById(id);
    }
}