package br.udesc.smartain.restsmartainproject.configuration.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.logging.Logger;

@Configuration
@Profile("test")
public class TestEnvironmentConfig implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        Logger
                .getAnonymousLogger()
                .info("Test Environment Working...");
        Logger
                .getAnonymousLogger()
                .info("Running Test Environment on the port: 8090");
    }

}
