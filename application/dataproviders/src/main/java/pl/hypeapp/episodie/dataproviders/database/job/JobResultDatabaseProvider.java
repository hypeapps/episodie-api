package pl.hypeapp.episodie.dataproviders.database.job;

import pl.hypeapp.episodie.core.entity.database.JobResult;
import pl.hypeapp.episodie.core.usecase.job.RecordJobResult;

public class JobResultDatabaseProvider implements RecordJobResult {

    private final JobResultRepository jobResultRepository;

    public JobResultDatabaseProvider(JobResultRepository jobResultRepository) {
        this.jobResultRepository = jobResultRepository;
    }

    @Override
    public void insertResult(JobResult jobResult) {
        jobResultRepository.save(jobResult);
    }

}
