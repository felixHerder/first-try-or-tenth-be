package com.felixherder.ftotbe.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(dotenvEntry ->
                System.setProperty(dotenvEntry.getKey(), dotenvEntry.getValue()));
    }
}
