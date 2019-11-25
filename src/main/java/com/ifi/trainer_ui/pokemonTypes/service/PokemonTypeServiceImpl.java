package com.ifi.trainer_ui.pokemonTypes.service;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    @Autowired
    private RestTemplate restTemplate;

    private String serviceUrl;

    public List<PokemonType> listPokemonsTypes() {
        var resultat = restTemplate.getForObject(serviceUrl+"/pokemon-types/",PokemonType[].class);
        List<PokemonType> list= new ArrayList<>();
        for(PokemonType p : resultat){
            list.add(p);
        }
        return list;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.serviceUrl = pokemonServiceUrl;
    }
}