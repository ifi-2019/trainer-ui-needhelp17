package com.ifi.pokemon_ui.trainers.service;

import com.ifi.pokemon_ui.controller.SecurityControllerAdvice;
import com.ifi.pokemon_ui.pokemonTypes.bo.PokemonType;
import com.ifi.pokemon_ui.pokemonTypes.service.PokemonTypeService;
import com.ifi.pokemon_ui.trainers.bo.Pokemon;
import com.ifi.pokemon_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainersServiceImpl implements TrainersService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonTypeService pokemonTypeService;

    @Autowired
    private SecurityControllerAdvice securityControllerAdvice;

    private String serviceUrl;

    public List<Trainer> listTrainers() {
        var res = restTemplate.getForObject(serviceUrl+"/trainers/",Trainer[].class);
        List<Trainer> trainers = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        for (Trainer t : res){
            if(!t.getName().equals(userDetails.getUsername())) {
                for (Pokemon p : t.getTeam()) {
                    PokemonType pokemonType = pokemonTypeService.getpokemonType(p.getPokemonType());
                    p.setUrlImg(pokemonType.getSprites().getFront_default());
                    p.setName(pokemonType.getName());
                }
                trainers.add(t);
            }
        }
        return trainers;
    }

    @Override
    public Trainer getTrainer(String name) {
        var res = restTemplate.getForObject(serviceUrl+"/trainers/"+name,Trainer.class);
        for(Pokemon p : res.getTeam()){
            PokemonType pokemonType = pokemonTypeService.getpokemonType(p.getPokemonType());
            p.setUrlImg(pokemonType.getSprites().getFront_default());
            p.setName(pokemonType.getName());
        }
        return res;
    }

    @Override
    public void addTrainer(String trainerName) {
        Trainer t = new Trainer(trainerName);
        this.restTemplate.postForLocation(serviceUrl+"/trainers/",t);
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${trainer.service.url}")
    public void setTrainerServiceUrl(String trainerServiceUrl) {
        this.serviceUrl = trainerServiceUrl;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        pokemonTypeService = pokemonTypeService;
    }
}
