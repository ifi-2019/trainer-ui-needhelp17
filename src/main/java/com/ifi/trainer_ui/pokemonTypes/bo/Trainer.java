package com.ifi.trainer_ui.pokemonTypes.bo;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Trainer {

    @Id
    private String name;

    @ElementCollection
    private List<PokemonType> team;

    public Trainer() {
    }

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonType> getTeam() {
        return team;
    }

    public void setTeam(List<PokemonType> team) {
        this.team = team;
    }
}