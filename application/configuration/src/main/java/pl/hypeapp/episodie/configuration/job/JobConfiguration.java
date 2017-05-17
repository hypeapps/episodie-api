package pl.hypeapp.episodie.configuration.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.core.usecase.job.RecordJobResultUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.update.UpdateTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular.CollectImdbMostPopularTvShowsJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular.CollectImdbMostPopularTvShowsJobResult;
import pl.hypeapp.episodie.entrypoints.job.tvshow.top.CollectImdbTopTvShowsJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.top.CollectImdbTopTvShowsJobResult;
import pl.hypeapp.episodie.entrypoints.job.tvshow.update.UpdateTvShowsJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.update.UpdateTvShowsJobResult;

@Configuration
public class JobConfiguration {

    @Bean
    public CollectImdbTopTvShowsJobResult collectImdbTopTvShowsJobResult(RecordJobResultUseCase recordJobResultUseCase) {
        return new CollectImdbTopTvShowsJobResult(recordJobResultUseCase);
    }

    @Bean
    public ScheduledJob collectImdbTopTvShowsJob(CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase,
                                                 CollectImdbTopTvShowsJobResult collectImdbTopTvShowsJobResult) {
        return new CollectImdbTopTvShowsJob(collectImdbTopTvShowsUseCase, collectImdbTopTvShowsJobResult);
    }

    @Bean
    public UpdateTvShowsJobResult updateTvShowsJobResult(RecordJobResultUseCase recordJobResultUseCase) {
        return new UpdateTvShowsJobResult(recordJobResultUseCase);
    }

    @Bean
    public ScheduledJob updateTvShowsJob(UpdateTvShowsUseCase updateTvShowsUseCase, UpdateTvShowsJobResult updateTvShowsJobResult) {
        return new UpdateTvShowsJob(updateTvShowsUseCase, updateTvShowsJobResult);
    }

    @Bean
    public CollectImdbMostPopularTvShowsJobResult collectImdbMostPopularTvShowsJobResult(RecordJobResultUseCase recordJobResultUseCase) {
        return new CollectImdbMostPopularTvShowsJobResult(recordJobResultUseCase);
    }

    @Bean
    ScheduledJob collectImdbMostPopularTvShowsJob(CollectImdbMostPopularTvShowsUseCase collectImdbMostPopularTvShowsUseCase,
                                                  CollectImdbMostPopularTvShowsJobResult collectImdbMostPopularTvShowsJobResult) {
        return new CollectImdbMostPopularTvShowsJob(collectImdbMostPopularTvShowsUseCase, collectImdbMostPopularTvShowsJobResult);
    }

}

