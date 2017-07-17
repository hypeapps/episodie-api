package pl.hypeapp.episodie.configuration.dataprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.dataproviders.database.job.JobResultDatabaseProvider;
import pl.hypeapp.episodie.dataproviders.database.job.JobResultRepository;
import pl.hypeapp.episodie.dataproviders.database.mostpopular.MostPopularDatabaseProvider;
import pl.hypeapp.episodie.dataproviders.database.mostpopular.TvShowMostPopularRepository;
import pl.hypeapp.episodie.dataproviders.database.premieres.PremieresDatabaseProvider;
import pl.hypeapp.episodie.dataproviders.database.premieres.TvShowPremieresRepository;
import pl.hypeapp.episodie.dataproviders.database.search.SearchTvShowDatabaseProvider;
import pl.hypeapp.episodie.dataproviders.database.toplist.TopListDatabaseProvider;
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
    public TopListDatabaseProvider topListDatabaseProvider(TvShowTopListRepository tvShowTopListRepository) {
        return new TopListDatabaseProvider(tvShowTopListRepository);
    }

    @Bean
    public MostPopularDatabaseProvider mostPopularDatabaseProvider(TvShowMostPopularRepository tvShowMosPopularRepository) {
        return new MostPopularDatabaseProvider(tvShowMosPopularRepository);
    }

    @Bean
    public SearchTvShowDatabaseProvider searchTvShowDatabaseProvider() {
        return new SearchTvShowDatabaseProvider();
    }

    @Bean
    public PremieresDatabaseProvider premieresDatabaseProvider(TvShowPremieresRepository tvShowPremieresRepository) {
        return new PremieresDatabaseProvider(tvShowPremieresRepository);
    }

    @Bean
    public JobResultDatabaseProvider jobResultDatabaseProvider(JobResultRepository jobResultRepository) {
        return new JobResultDatabaseProvider(jobResultRepository);
    }

}
