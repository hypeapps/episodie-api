package pl.hypeapp.episodie.core.usecase.job;

import pl.hypeapp.episodie.core.entity.database.JobResult;

import java.sql.Timestamp;
import java.util.Date;

public class RecordJobResultUseCase {

    private static final String JOB_SUCCESSFUL = "JOB SUCCESS";

    private static final String JOB_UNSUCCESSFUL = "JOB FAILED BECAUSE: ";

    private final RecordJobResult recordJobResult;

    private final SendSmsAboutFailedJob sendSmsAboutFailedJob;

    public RecordJobResultUseCase(RecordJobResult recordJobResult, SendSmsAboutFailedJob sendSmsAboutFailedJob) {
        this.recordJobResult = recordJobResult;
        this.sendSmsAboutFailedJob = sendSmsAboutFailedJob;
    }

    public void recordSuccessfulJob(String jobName, Date date) {
        Timestamp jobTimestamp = createTimestamp(date.getTime());
        JobResult jobResult = new JobResult(jobName, JOB_SUCCESSFUL, jobTimestamp);
        recordJobResult.insertResult(jobResult);
    }

    public void recordUnsuccessfulJob(String jobName, String message, Date date) {
        Timestamp jobTimestamp = createTimestamp(date.getTime());
        JobResult jobResult = new JobResult(jobName, JOB_UNSUCCESSFUL + message, jobTimestamp);
        recordJobResult.insertResult(jobResult);
        sendSmsAboutFailedJob.send(jobResult);
    }

    private Timestamp createTimestamp(long time) {
        return new Timestamp(time);
    }

}
