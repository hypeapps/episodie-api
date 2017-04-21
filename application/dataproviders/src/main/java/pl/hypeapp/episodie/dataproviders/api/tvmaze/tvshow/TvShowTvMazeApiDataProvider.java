package pl.hypeapp.episodie.dataproviders.api.tvmaze.tvshow;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TvShowTvMazeApiDataProvider implements GetTvShowFromApi {
    private static final Logger LOOGER = LoggerFactory.getLogger(TvShowTvMazeApiDataProvider.class);
    private final RestTemplate restTemplate;
    private final Retryer<TvShowRemote> retryer;

    public TvShowTvMazeApiDataProvider(RestTemplate restTemplate, Retryer<TvShowRemote> retryer) {
        this.restTemplate = restTemplate;
        this.retryer = retryer;
    }

    @Override
    public Optional<TvShowRemote> getTvShowByMazeId(TvMazeId tvMazeId) {
        try {
            TvShowRemote tvShowRemote = retryer.call(callForTvShow(tvMazeId.getId()));
            return Optional.of(tvShowRemote);
        } catch (RetryException | ExecutionException e) {
            LOOGER.info(e.getMessage());
        }
        return Optional.empty();
    }

    private Callable<TvShowRemote> callForTvShow(String id) {
        return () -> restTemplate.getForObject("http://api.tvmaze.com/shows/" + id + "?embed[]=seasons&embed[]=episodes", TvShowRemote.class);
    }

}
