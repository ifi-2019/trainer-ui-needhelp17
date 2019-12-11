package com.ifi.pokemon_ui.trainers.service;

import com.ifi.pokemon_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainersServiceImpl implements TrainersService {
    @Autowired
    private RestTemplate restTemplate;

    private String serviceUrl;

    public List<Trainer> listTrainers() {
        var res = restTemplate.getForObject(serviceUrl+"/trainers/",Trainer[].class);
        List<Trainer> trainers = new ArrayList<>();
        for (Trainer t : res){
            trainers.add(t);
        }
        return trainers;
    }

    @Override
    public Trainer getTrainer(String name) {
        var res = restTemplate.getForObject(serviceUrl+"/trainers/"+name,Trainer.class);
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
}
