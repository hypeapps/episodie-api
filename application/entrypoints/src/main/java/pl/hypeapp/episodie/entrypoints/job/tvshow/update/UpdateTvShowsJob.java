package pl.hypeapp.episodie.entrypoints.job.tvshow.update;

import pl.hypeapp.core.usecase.tvshow.update.UpdateTvShowsException;
import pl.hypeapp.core.usecase.tvshow.update.UpdateTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.TimeUnit;

public class UpdateTvShowsJob implements ScheduledJob {
    private static final String JOB_NAME = "UpdateTvShowsJob";
    private final UpdateTvShowsJobResult updateTvShowsJobResult;
    private final UpdateTvShowsUseCase updateTvShowsUseCase;

    public UpdateTvShowsJob(UpdateTvShowsUseCase updateTvShowsJobUseCase, UpdateTvShowsJobResult updateTvShowsJobResult) {
        this.updateTvShowsJobResult = updateTvShowsJobResult;
        this.updateTvShowsUseCase = updateTvShowsJobUseCase;
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
        return 1;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.DAYS;
    }

    @Override
    public void run() {
        try {
            updateTvShowsUseCase.update();
            updateTvShowsJobResult.recordJobSuccessfulResult();
        } catch (UpdateTvShowsException e) {
            updateTvShowsJobResult.recordJobUnsuccessfulResult(e.getMessage());
        }
    }

}
