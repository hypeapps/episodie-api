package pl.hypeapp.episodie.entrypoints.job.tvshow.premieres;

import org.springframework.scheduling.annotation.Scheduled;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.CollectImdbTvShowsPremieresException;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.CollectPremieresUseCase;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.TimeUnit;

public class CollectImdbPremieresJob implements ScheduledJob {

    private static final String JOB_NAME = "CollectImdbPremieresJob";

    private final CollectPremieresUseCase collectPremieresUseCase;

    private final CollectImdbPremieresJobResult collectImdbPremieresJobResult;

    public CollectImdbPremieresJob(CollectPremieresUseCase collectPremieresUseCase,
                                   CollectImdbPremieresJobResult collectImdbPremieresJobResult) {
        this.collectPremieresUseCase = collectPremieresUseCase;
        this.collectImdbPremieresJobResult = collectImdbPremieresJobResult;
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
        return 5;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.DAYS;
    }

    @Override
    @Scheduled(cron = "0 0 12 1 * *")
    public void run() {
        try {
            collectPremieresUseCase.collect();
            collectImdbPremieresJobResult.recordJobSuccessfulResult(JOB_NAME);
        } catch (CollectImdbTvShowsPremieresException e) {
            collectImdbPremieresJobResult.recordJobUnsuccessfulResult(JOB_NAME, e.getMessage());
        }
    }

}
