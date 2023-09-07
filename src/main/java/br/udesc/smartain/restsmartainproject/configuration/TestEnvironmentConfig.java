package br.udesc.smartain.restsmartainproject.configuration;

import org.springframework.boot.CommandLineRunner;
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
    }
}
