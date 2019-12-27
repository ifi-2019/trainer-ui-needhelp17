package com.ifi.pokemon_ui.config;


import com.ifi.pokemon_ui.trainers.bo.Trainer;
import com.ifi.pokemon_ui.trainers.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TrainersService trainersService;

    public TrainersService getTrainersService() {
        return trainersService;
    }

    public void setTrainersService(TrainersService trainersService) {
        this.trainersService = trainersService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
       return new UserDetailsService(trainersService);
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/registerTrainer","/").permitAll();
        super.configure(http);
    }*/

    public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

        @Autowired
        private TrainersService trainersService;

        public UserDetailsService(TrainersService trainersService) {
            this.trainersService=trainersService;
        }

        public UserDetails loadUserByUsername(String name) {
            var trainer = trainersService.getTrainer(name);
            if(trainer == null){
                throw new BadCredentialsException("No such user");
            }
            UserDetails ud;
            GrantedAuthority grantedAuthority = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_USER";
                }
            };
            ud = User.withUsername(trainer.getName()).password(trainer.getPassword()).authorities(grantedAuthority).build();
            return ud;
        }
    }
}
