package pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular;

import pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsException;
import pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.TimeUnit;

public class CollectImdbMostPopularTvShowsJob implements ScheduledJob {

    private static final String IMDB_MOST_POPULAR_TV_SHOWS_URL = "http://www.imdb.com/chart/tvmeter";

    private static final String JOB_NAME = "CollectImdbMostPopularTvShowsJob";

    private final CollectImdbMostPopularTvShowsUseCase collectImdbMostPopularTvShowsUseCase;

    private final CollectImdbMostPopularTvShowsJobResult collectImdbMostPopularTvShowsJobResult;

    public CollectImdbMostPopularTvShowsJob(CollectImdbMostPopularTvShowsUseCase collectImdbMostPopularTvShowsUseCase,
                                            CollectImdbMostPopularTvShowsJobResult collectImdbMostPopularTvShowsJobResult) {
        this.collectImdbMostPopularTvShowsUseCase = collectImdbMostPopularTvShowsUseCase;
        this.collectImdbMostPopularTvShowsJobResult = collectImdbMostPopularTvShowsJobResult;
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
            collectImdbMostPopularTvShowsUseCase.collect(IMDB_MOST_POPULAR_TV_SHOWS_URL);
            collectImdbMostPopularTvShowsJobResult.recordJobSuccessfulResult();
        } catch (CollectImdbMostPopularTvShowsException e) {
            collectImdbMostPopularTvShowsJobResult.recordJobUnsuccessfulResult(e.getMessage());
        }
    }

}
