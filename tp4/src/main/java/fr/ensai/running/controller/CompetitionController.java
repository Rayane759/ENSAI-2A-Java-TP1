package fr.ensai.running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.ensai.running.model.Competition;
import fr.ensai.running.service.CompetitionService;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping
    public String getAllCompetitions(Model model) {
        model.addAttribute("competitions", competitionService.getAllCompetitions());
        return "allCompetitions";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompetition(@PathVariable(value = "id") Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        if (competition != null) {
            competitionService.deleteCompetition(id);
        }
        return "redirect:/competition";
    }
}
