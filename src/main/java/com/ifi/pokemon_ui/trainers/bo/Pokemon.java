package com.ifi.pokemon_ui.trainers.bo;

public class Pokemon {

    private int pokemonType;
    private String name;
    private int level;
    private String urlImg;

    public Pokemon() {
    }

    public Pokemon(int pokemonType, int level) {
        this.pokemonType = pokemonType;
        this.level = level;
    }
    public Pokemon(int pokemonType, int level,String url) {
        this.pokemonType = pokemonType;
        this.level = level;
        this.urlImg = url;
    }

    public int getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(int pokemonType) {
        this.pokemonType = pokemonType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
