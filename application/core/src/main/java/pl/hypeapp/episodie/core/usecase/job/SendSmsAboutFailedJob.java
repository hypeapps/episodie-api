package pl.hypeapp.episodie.core.usecase.job;

import pl.hypeapp.episodie.core.entity.database.JobResult;

public interface SendSmsAboutFailedJob {

    void send(JobResult jobResult);

}
