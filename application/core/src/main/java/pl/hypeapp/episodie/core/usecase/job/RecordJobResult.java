package pl.hypeapp.episodie.core.usecase.job;

import pl.hypeapp.episodie.core.entity.database.JobResult;

@FunctionalInterface
public interface RecordJobResult {

    void insertResult(JobResult jobResult);

}
