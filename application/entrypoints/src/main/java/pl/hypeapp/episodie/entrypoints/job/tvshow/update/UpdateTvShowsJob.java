package pl.hypeapp.episodie.entrypoints.job.tvshow.update;

import pl.hypeapp.episodie.core.usecase.tvshow.update.UpdateTvShowsException;
import pl.hypeapp.episodie.core.usecase.tvshow.update.UpdateTvShowsUseCase;
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
        return 2;
    }

    @Override
    public long getPeriod() {
        return 30;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.HOURS;
    }

    @Override
    public void run() {
        try {
            updateTvShowsUseCase.update();
            updateTvShowsJobResult.recordJobSuccessfulResult(JOB_NAME);
        } catch (UpdateTvShowsException e) {
            updateTvShowsJobResult.recordJobUnsuccessfulResult(JOB_NAME, e.getMessage());
        }
    }

}
