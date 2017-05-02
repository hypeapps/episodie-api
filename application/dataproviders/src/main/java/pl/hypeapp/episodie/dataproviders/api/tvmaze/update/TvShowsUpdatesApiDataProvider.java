package pl.hypeapp.episodie.dataproviders.api.tvmaze.update;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.core.entity.api.tvmaze.TvShowsUpdatesRemote;
import pl.hypeapp.core.usecase.tvshow.update.GetTvShowsUpdates;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TvShowsUpdatesApiDataProvider implements GetTvShowsUpdates {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowsUpdatesApiDataProvider.class);
    private final RestTemplate restTemplate;
    private final Retryer<TvShowsUpdatesRemote> retryer;

    public TvShowsUpdatesApiDataProvider(RestTemplate restTemplate, Retryer<TvShowsUpdatesRemote> retryer) {
        this.restTemplate = restTemplate;
        this.retryer = retryer;
    }

    @Override
    public Optional<TvShowsUpdatesRemote> getUpdates() {
        try {
            TvShowsUpdatesRemote tvShowsUpdatesRemote = retryer.call(callForUpdates());
            return Optional.of(tvShowsUpdatesRemote);
        } catch (RetryException | ExecutionException e) {
            LOGGER.info(e.getMessage());
        }
        return Optional.empty();
    }

    private Callable<TvShowsUpdatesRemote> callForUpdates() {
        return () -> {
            Map<String, Integer> updates = (Map<String, Integer>) restTemplate.getForObject("http://api.tvmaze.com/updates/shows", Map.class);
            return new TvShowsUpdatesRemote(updates);
        };
    }

}
