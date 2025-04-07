package fr.ensai.running.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.service.AthleteService;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Get all athletes
     */
    @GetMapping("/athlete")
    public List<Athlete> allAthletes() {
        return athleteService.findAll();
    }

    /**
     * Get an Athlete by ID
     */
    @GetMapping("/athlete/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Long id) {
        Athlete athlete = athleteService.findById(id);
        if (athlete != null) {
            return ResponseEntity.ok(athlete);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Create a new Athlete
     */
    @PostMapping("/athlete")
    public ResponseEntity<Athlete> createAthlete(@RequestBody Athlete athlete) {
        Athlete createdAthlete = athleteService.save(athlete);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAthlete);
    }

    /**
     * Delete an Athlete by ID
     */
    @DeleteMapping("/athlete/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Long id) {
        Athlete athlete = athleteService.findById(id);
        if (athlete != null) {
            athleteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
