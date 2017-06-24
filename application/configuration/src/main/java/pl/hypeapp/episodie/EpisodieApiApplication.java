package pl.hypeapp.episodie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "pl.hypeapp.episodie.configuration")
@EnableJpaRepositories(basePackages = "pl.hypeapp.episodie.dataproviders")
@EntityScan("pl.hypeapp.episodie.core.entity")
public class EpisodieApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EpisodieApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EpisodieApiApplication.class);
    }

}
