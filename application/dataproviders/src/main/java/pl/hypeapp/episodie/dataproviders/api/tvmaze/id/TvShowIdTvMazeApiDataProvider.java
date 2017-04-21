package pl.hypeapp.episodie.dataproviders.api.tvmaze.id;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.GetTvShowIdFromApi;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TvShowIdTvMazeApiDataProvider implements GetTvShowIdFromApi {
    private static final Logger LOOGER = LoggerFactory.getLogger(TvShowIdTvMazeApiDataProvider.class);
    private static final String API_ENDPOINT = "http://api.tvmaze.com/lookup/shows?imdb=";
    private final RestTemplate restTemplate;
    private final Retryer<TvMazeId> retryer;

    public TvShowIdTvMazeApiDataProvider(RestTemplate restTemplate, Retryer<TvMazeId> retryer) {
        this.restTemplate = restTemplate;
        this.retryer = retryer;
    }

    @Override
    public Optional<TvMazeId> getTvShowIdByImdbId(String imdbId) {
        try {
            TvMazeId tvMazeId = retryer.call(callForId(imdbId));
            return Optional.of(tvMazeId);
        } catch (RetryException | ExecutionException e) {
            LOOGER.info(e.getMessage());
        }
        return Optional.empty();
    }

    private Callable<TvMazeId> callForId(String imdbId) {
        return () -> restTemplate.getForObject(API_ENDPOINT + imdbId, TvMazeId.class);
    }

}
