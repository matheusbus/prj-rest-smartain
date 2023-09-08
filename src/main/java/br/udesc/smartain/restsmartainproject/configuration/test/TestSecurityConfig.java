package br.udesc.smartain.restsmartainproject.configuration.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Profile("test")
public class TestSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails matheus = User.builder()
                .username("matheus")
                .password("12345")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(matheus);
    }

}
