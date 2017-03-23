package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.dataproviders.database.EpisodeDatabaseProvider;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public EpisodeDatabaseProvider episodeDatabaseProvider() {
        return new EpisodeDatabaseProvider();
    }

}
