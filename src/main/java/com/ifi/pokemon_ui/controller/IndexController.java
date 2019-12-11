package com.ifi.pokemon_ui.controller;

import com.ifi.pokemon_ui.trainers.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    TrainersService trainersService;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/registerTrainer")
    public ModelAndView registerNewTrainer(String trainerName) {
       var modelandview = new ModelAndView("register");
       modelandview.getModel().putIfAbsent("name",trainerName);
       trainersService.addTrainer(trainerName);
       return modelandview;
    }
}
