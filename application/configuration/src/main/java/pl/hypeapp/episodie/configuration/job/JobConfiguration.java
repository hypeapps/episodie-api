package pl.hypeapp.episodie.configuration.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.top.CollectImdbTopTvShowsJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.top.CollectImdbTopTvShowsJobResult;

@Configuration
public class JobConfiguration {

    @Bean
    public CollectImdbTopTvShowsJobResult imdbTopEpisodesExtractJobResult() {
        return new CollectImdbTopTvShowsJobResult();
    }

    @Bean
    public ScheduledJob imdbTopEpisodesExtractJob(CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase,
                                                  CollectImdbTopTvShowsJobResult collectImdbTopTvShowsJobResult) {
        return new CollectImdbTopTvShowsJob(collectImdbTopTvShowsUseCase, collectImdbTopTvShowsJobResult);
    }
}
