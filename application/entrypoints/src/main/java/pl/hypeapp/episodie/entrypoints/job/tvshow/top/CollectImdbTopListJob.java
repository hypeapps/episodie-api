package pl.hypeapp.episodie.entrypoints.job.tvshow.top;

import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopListUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsException;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.TimeUnit;

public class CollectImdbTopListJob implements ScheduledJob {

    private static final String IMDB_TOP_TV_SHOWS_URL = "http://www.imdb.com/chart/toptv";

    private static final String JOB_NAME = "CollectImdbTopListJob";

    private final CollectImdbTopListUseCase collectImdbTopListUseCase;

    private final CollectImdbTopListJobResult collectImdbTopListJobResult;

    public CollectImdbTopListJob(CollectImdbTopListUseCase collectImdbTopListUseCase,
                                 CollectImdbTopListJobResult collectImdbTopListJobResult) {
        this.collectImdbTopListUseCase = collectImdbTopListUseCase;
        this.collectImdbTopListJobResult = collectImdbTopListJobResult;
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
        return 3;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.DAYS;
    }

    @Override
    public void run() {
        try {
            collectImdbTopListUseCase.collect(IMDB_TOP_TV_SHOWS_URL);
            collectImdbTopListJobResult.recordJobSuccessfulResult(JOB_NAME);
        } catch (CollectImdbTopTvShowsException e) {
            collectImdbTopListJobResult.recordJobUnsuccessfulResult(JOB_NAME, e.getMessage());
        }
    }

}
