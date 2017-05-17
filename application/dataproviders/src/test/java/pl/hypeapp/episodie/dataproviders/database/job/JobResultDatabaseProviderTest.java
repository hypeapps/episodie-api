package pl.hypeapp.episodie.dataproviders.database.job;

import org.junit.Test;
import pl.hypeapp.episodie.core.entity.database.JobResult;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;

public class JobResultDatabaseProviderTest {

    private JobResultRepository jobResultRepository = mock(JobResultRepository.class);

    private JobResultDatabaseProvider jobResultDatabaseProvider = new JobResultDatabaseProvider(jobResultRepository);

    @Test
    public void shouldInsertResultToDatabase() throws Exception {
        JobResult jobResult = new JobResult("NAME", "SUCCESS", new Timestamp(1323123123));

        jobResultDatabaseProvider.insertResult(jobResult);

        verify(jobResultRepository, times(1)).save(jobResult);
    }

}