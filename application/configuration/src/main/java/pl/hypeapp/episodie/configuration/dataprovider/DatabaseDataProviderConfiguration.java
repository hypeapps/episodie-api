package pl.hypeapp.episodie.configuration.dataprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.dataproviders.database.toplist.TvShowTopListDatabaseProvider;
import pl.hypeapp.episodie.dataproviders.database.toplist.TvShowTopListRepository;
import pl.hypeapp.episodie.dataproviders.database.tvshow.TvShowDatabaseProvider;
import pl.hypeapp.episodie.dataproviders.database.tvshow.TvShowRepository;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public TvShowDatabaseProvider tvShowDatabaseProvider(TvShowRepository tvShowRepository) {
        return new TvShowDatabaseProvider(tvShowRepository);
    }

    @Bean
    public TvShowTopListDatabaseProvider tvShowTopListDatabaseProvider(TvShowRepository tvShowRepository, TvShowTopListRepository tvShowTopListRepository) {
        return new TvShowTopListDatabaseProvider(tvShowRepository, tvShowTopListRepository);
    }

}
