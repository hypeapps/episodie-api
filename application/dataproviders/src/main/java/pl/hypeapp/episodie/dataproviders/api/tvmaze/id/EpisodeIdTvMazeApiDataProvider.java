package pl.hypeapp.episodie.dataproviders.api.tvmaze.id;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.core.entity.tvmazeid.TvMazeId;
import pl.hypeapp.core.usecase.episode.extractimdbtoptvshows.EpisodeIdNotFoundException;
import pl.hypeapp.core.usecase.episode.extractimdbtoptvshows.GetEpisodeIdFromApi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class EpisodeIdTvMazeApiDataProvider implements GetEpisodeIdFromApi {
    private static final Logger LOOGER = LoggerFactory.getLogger(EpisodeIdTvMazeApiDataProvider.class);
    private static final String API_ENDPOINT = "http://api.tvmaze.com/lookup/shows?imdb=";
    private final RestTemplate restTemplate;
    private final Retryer<TvMazeId> retryer;

    public EpisodeIdTvMazeApiDataProvider(RestTemplate restTemplate, Retryer<TvMazeId> retryer) {
        this.restTemplate = restTemplate;
        this.retryer = retryer;
    }

    @Override
    public TvMazeId getEpisodeIdByImdbId(String imdbId) {
        try {
            return retryer.call(callForId(imdbId));
        } catch (RetryException | ExecutionException e) {
            LOOGER.info(e.getMessage());
            throw new EpisodeIdNotFoundException();
        }
    }

    private Callable<TvMazeId> callForId(String imdbId) {
        return () -> restTemplate.getForObject(API_ENDPOINT + imdbId, TvMazeId.class);
    }
}
