package pl.hypeapp.episodie.configuration.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.entrypoints.job.ScheduledJob;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class JobSchedulerConfiguration {

//    private static final Logger LOGGER = LoggerFactory.getLogger(JobSchedulerConfiguration.class);

    @Bean
    public ScheduledExecutorService scheduledExecutorService(ScheduledJob... jobs) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        for (ScheduledJob job : jobs) {
            long initialDelay = job.getInitialDelay();
            long period = job.getPeriod();
            TimeUnit timeUnit = job.getTimeUnit();
//            LOGGER.info("Scheduling job {} to run every {} {}, after an initial {} {}", job.getName(), period, timeUnit, initialDelay, timeUnit);
            scheduledExecutorService.scheduleAtFixedRate(job, initialDelay, period, timeUnit);
        }

        return scheduledExecutorService;
    }

}