package pl.hypeapp.episodie;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "pl.hypeapp.episodie.configuration")
@EnableAdminServer
@EnableJpaRepositories
public class EpisodieApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EpisodieApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EpisodieApiApplication.class);
    }
}
