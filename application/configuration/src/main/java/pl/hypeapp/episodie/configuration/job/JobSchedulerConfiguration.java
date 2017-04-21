package pl.hypeapp.episodie.configuration.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class JobSchedulerConfiguration {

    @Bean
    public ScheduledExecutorService scheduledExecutorService(ScheduledJob... jobs) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        for (ScheduledJob job : jobs) {
            long initialDelay = job.getInitialDelay();
            long period = job.getPeriod();
            TimeUnit timeUnit = job.getTimeUnit();
            scheduledExecutorService.scheduleAtFixedRate(job, initialDelay, period, timeUnit);
        }

        return scheduledExecutorService;
    }

}
