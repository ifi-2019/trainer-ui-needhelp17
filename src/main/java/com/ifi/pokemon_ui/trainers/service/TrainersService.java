package com.ifi.pokemon_ui.trainers.service;

import com.ifi.pokemon_ui.trainers.bo.Trainer;

import java.util.List;

public interface TrainersService {

    List<Trainer> listTrainers();
    Trainer getTrainer(String name);
    void addTrainer(String trainerName);
}
