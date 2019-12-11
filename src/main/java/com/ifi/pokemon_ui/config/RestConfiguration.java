package com.ifi.pokemon_ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    private String username;

    @Value("${trainer.service.password}")
    private String password;

    @Bean
    RestTemplate trainerApiRestTemplate(){
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new BasicAuthenticationInterceptor(username,password));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}