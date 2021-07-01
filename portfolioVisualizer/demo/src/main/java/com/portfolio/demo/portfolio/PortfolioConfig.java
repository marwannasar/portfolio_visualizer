package com.portfolio.demo.portfolio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PortfolioConfig {

    @Bean
    CommandLineRunner commandLineRunner(PortfolioRepository repository){
        return args -> {
            holdings msft = new holdings("MSFT", 200, 5);
            holdings bb = new holdings("BB", 13, 10);

            repository.saveAll(List.of(msft, bb));
        };

    }
}
