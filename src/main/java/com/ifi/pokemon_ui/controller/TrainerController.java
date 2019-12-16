package com.ifi.pokemon_ui.controller;

import com.ifi.pokemon_ui.pokemonTypes.bo.PokemonType;
import com.ifi.pokemon_ui.pokemonTypes.service.PokemonTypeService;
import com.ifi.pokemon_ui.trainers.bo.Pokemon;
import com.ifi.pokemon_ui.trainers.bo.Trainer;
import com.ifi.pokemon_ui.trainers.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {

    @Autowired
    private TrainersService trainerservice;

    @Autowired
    private PokemonTypeService pokemonTypeService;

    @GetMapping("/trainers")
    public ModelAndView trainer(){
        var modelandview = new ModelAndView("trainers");
        var listetrainers = trainerservice.listTrainers();
        List<PokemonType> pokemonTypeList = new ArrayList<PokemonType>();
        for(Trainer t : listetrainers){
            for(Pokemon pokemon: t.getTeam()) {
                PokemonType pt = pokemonTypeService.getpokemonType(pokemon.getPokemonType());
                pokemonTypeList.add(pt);
            }
        }
        modelandview.getModel().putIfAbsent("pokemonTypeList",pokemonTypeList);
        modelandview.getModel().putIfAbsent("trainers",listetrainers);
        return modelandview;
    }

    @GetMapping("/profile")
    public ModelAndView getTrainer(){
        var modelandview = new ModelAndView("trainers");
        List<Trainer> trainers = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Trainer t = trainerservice.getTrainer(userDetails.getUsername());
        modelandview.getModel().putIfAbsent("trainers",t);
        return modelandview;
    }

    @Autowired
    public void setTrainerService(TrainersService trainerService) {
        trainerservice = trainerService;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        pokemonTypeService = pokemonTypeService;
    }
}