package pl.hypeapp.episodie.entrypoints.job.tvshow.top;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CollectImdbTopTvShowsJobResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectImdbTopTvShowsJobResult.class);
    private final Date date = new Date();

    public void recordJobSuccessfulResult() {
        LOGGER.info(date.toString() + " JOB SUCCESSFUL");
    }

    public void recordJobUnsuccessfulResult(String message) {
        System.out.println(date.toString() + " JOB FAILED BECAUSE: " + message);
    }

}
