package pl.hypeapp.episodie.entrypoints.job.tvshow.top;

import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsException;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.TimeUnit;

public class CollectImdbTopTvShowsJob implements ScheduledJob {
    private static final String IMDB_TOP_TV_SHOWS_URL = "http://www.imdb.com/chart/toptv";
    private final CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase;
    private final CollectImdbTopTvShowsJobResult collectImdbTopTvShowsJobResult;

    public CollectImdbTopTvShowsJob(CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase,
                                    CollectImdbTopTvShowsJobResult collectImdbTopTvShowsJobResult) {
        this.collectImdbTopTvShowsUseCase = collectImdbTopTvShowsUseCase;
        this.collectImdbTopTvShowsJobResult = collectImdbTopTvShowsJobResult;
    }

    @Override
    public String getName() {
        return "CollectImdbTopTvShowsJob";
    }

    @Override
    public long getInitialDelay() {
        return 0;
    }

    @Override
    public long getPeriod() {
        return 1;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.DAYS;
    }

    @Override
    public void run() {
        try {
            collectImdbTopTvShowsUseCase.collect(IMDB_TOP_TV_SHOWS_URL);
            collectImdbTopTvShowsJobResult.recordJobSuccessfulResult();
        } catch (CollectImdbTopTvShowsException e) {
            collectImdbTopTvShowsJobResult.recordJobUnsuccessfulResult(e.getMessage());
        }
    }

}
