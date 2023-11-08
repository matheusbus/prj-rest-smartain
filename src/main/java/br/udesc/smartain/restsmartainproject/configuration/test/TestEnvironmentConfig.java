package br.udesc.smartain.restsmartainproject.configuration.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.logging.Logger;

@Configuration
@Profile("test")
public class TestEnvironmentConfig implements CommandLineRunner, RepositoryRestConfigurer {

    @Override
    public void run(String... args) throws Exception {

        Logger
                .getAnonymousLogger()
                .info("Test Environment Working...");
        Logger
                .getAnonymousLogger()
                .info("Running Test Environment on the port: 8090");
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        cors.addMapping("/*")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "DELETE")
                .allowCredentials(false).maxAge(3600);
    }



}
