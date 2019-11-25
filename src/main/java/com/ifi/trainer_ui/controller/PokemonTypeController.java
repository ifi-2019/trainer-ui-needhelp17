package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.pokemonTypes.service.PokemonTypeService;
import com.ifi.trainer_ui.pokemonTypes.service.PokemonTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    @Autowired
    private PokemonTypeService pokeservice;

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        var modelandview = new ModelAndView("pokedex");
        var listepoke = pokeservice.listPokemonsTypes();
        modelandview.getModel().putIfAbsent("pokemonTypes",listepoke);
        return modelandview;
    }
    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        pokeservice = pokemonTypeService;
    }

}