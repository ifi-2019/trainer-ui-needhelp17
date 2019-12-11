package com.ifi.pokemon_ui.controller;

import com.ifi.pokemon_ui.trainers.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {

    @Autowired
    private TrainersService trainerservice;

    @GetMapping("/trainers")
    public ModelAndView trainer(){
        var modelandview = new ModelAndView("trainers");
        var listetrainers = trainerservice.listTrainers();
        modelandview.getModel().putIfAbsent("trainers",listetrainers);
        return modelandview;
    }

    @Autowired
    public void setPokemonTypeService(TrainersService trainerService) {
        trainerservice = trainerService;
    }

}