package pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular;

import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsException;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.TimeUnit;

public class CollectImdbMostPopularJob implements ScheduledJob {

    private static final String IMDB_MOST_POPULAR_TV_SHOWS_URL = "http://www.imdb.com/chart/tvmeter";

    private static final String JOB_NAME = "CollectImdbMostPopularJob";

    private final CollectImdbMostPopularUseCase collectImdbMostPopularUseCase;

    private final CollectImdbMostPopularJobResult collectImdbMostPopularJobResult;

    public CollectImdbMostPopularJob(CollectImdbMostPopularUseCase collectImdbMostPopularUseCase,
                                     CollectImdbMostPopularJobResult collectImdbMostPopularJobResult) {
        this.collectImdbMostPopularUseCase = collectImdbMostPopularUseCase;
        this.collectImdbMostPopularJobResult = collectImdbMostPopularJobResult;
    }

    @Override
    public String getName() {
        return JOB_NAME;
    }

    @Override
    public long getInitialDelay() {
        return 0;
    }

    @Override
    public long getPeriod() {
        return 2;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.DAYS;
    }

    @Override
    public void run() {
        try {
            collectImdbMostPopularUseCase.collect(IMDB_MOST_POPULAR_TV_SHOWS_URL);
            collectImdbMostPopularJobResult.recordJobSuccessfulResult(JOB_NAME);
        } catch (CollectImdbMostPopularTvShowsException e) {
            collectImdbMostPopularJobResult.recordJobUnsuccessfulResult(JOB_NAME, e.getMessage());
        }
    }

}
