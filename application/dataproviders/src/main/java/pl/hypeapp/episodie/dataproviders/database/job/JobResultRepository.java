package pl.hypeapp.episodie.dataproviders.database.job;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hypeapp.episodie.core.entity.database.JobResult;

public interface JobResultRepository extends JpaRepository<JobResult, Integer> {
}
