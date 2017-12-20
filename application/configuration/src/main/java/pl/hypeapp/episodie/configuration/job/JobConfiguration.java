package pl.hypeapp.episodie.configuration.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.core.usecase.job.RecordJobResultUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.CollectPremieresUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopListUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.update.UpdateTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular.CollectImdbMostPopularJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular.CollectImdbMostPopularJobResult;
import pl.hypeapp.episodie.entrypoints.job.tvshow.premieres.CollectImdbPremieresJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.premieres.CollectImdbPremieresJobResult;
import pl.hypeapp.episodie.entrypoints.job.tvshow.top.CollectImdbTopListJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.top.CollectImdbTopListJobResult;
import pl.hypeapp.episodie.entrypoints.job.tvshow.update.UpdateTvShowsJob;
import pl.hypeapp.episodie.entrypoints.job.tvshow.update.UpdateTvShowsJobResult;

@Configuration
public class JobConfiguration {

    @Bean
    public CollectImdbTopListJobResult collectImdbTopListJobResult(RecordJobResultUseCase recordJobResultUseCase) {
        return new CollectImdbTopListJobResult(recordJobResultUseCase);
    }

    @Bean
    public ScheduledJob collectImdbTopListJob(CollectImdbTopListUseCase collectImdbTopListUseCase,
                                              CollectImdbTopListJobResult collectImdbTopListJobResult) {
        return new CollectImdbTopListJob(collectImdbTopListUseCase, collectImdbTopListJobResult);
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
    public CollectImdbMostPopularJobResult collectImdbMostPopularJobResult(RecordJobResultUseCase recordJobResultUseCase) {
        return new CollectImdbMostPopularJobResult(recordJobResultUseCase);
    }

    @Bean
    public ScheduledJob collectImdbMostPopularJob(CollectImdbMostPopularUseCase collectImdbMostPopularUseCase,
                                                  CollectImdbMostPopularJobResult collectImdbMostPopularJobResult) {
        return new CollectImdbMostPopularJob(collectImdbMostPopularUseCase, collectImdbMostPopularJobResult);
    }

    @Bean
    public CollectImdbPremieresJobResult collectImdbPremieresJobResult(RecordJobResultUseCase recordJobResultUseCase) {
        return new CollectImdbPremieresJobResult(recordJobResultUseCase);
    }

    @Bean
    public ScheduledJob collectImdbPremieresJob(CollectPremieresUseCase collectPremieresUseCase,
                                                CollectImdbPremieresJobResult collectImdbPremieresJobResult) {
        return new CollectImdbPremieresJob(collectPremieresUseCase, collectImdbPremieresJobResult);
    }

}

