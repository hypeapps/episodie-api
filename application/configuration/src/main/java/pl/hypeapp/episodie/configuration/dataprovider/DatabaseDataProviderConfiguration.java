package pl.hypeapp.episodie.configuration.dataprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.dataproviders.database.TvShowDatabaseProvider;
import pl.hypeapp.episodie.dataproviders.database.TvShowRepository;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public TvShowDatabaseProvider tvShowDatabaseProvider(TvShowRepository tvShowRepository) {
        return new TvShowDatabaseProvider(tvShowRepository);
    }

}
