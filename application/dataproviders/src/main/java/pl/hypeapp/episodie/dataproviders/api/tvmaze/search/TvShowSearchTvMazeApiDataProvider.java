package pl.hypeapp.episodie.dataproviders.api.tvmaze.search;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.usecase.tvshow.search.SearchTvShowInApi;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TvShowSearchTvMazeApiDataProvider implements SearchTvShowInApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowSearchTvMazeApiDataProvider.class);

    private final RestTemplate restTemplate;

    private final Retryer<TvShowRemote> retryer;

    public TvShowSearchTvMazeApiDataProvider(RestTemplate restTemplate, Retryer<TvShowRemote> retreyer) {
        this.restTemplate = restTemplate;
        this.retryer = retreyer;
    }

    @Override
    public Optional<TvShowRemote> search(String query) {
        try {
            TvShowRemote tvShowRemote = retryer.call(searchForTvShow(query));
            return Optional.of(tvShowRemote);
        } catch (RetryException | ExecutionException e) {
            LOGGER.info(e.getMessage());
        }
        return Optional.empty();
    }

    private Callable<TvShowRemote> searchForTvShow(String query) {
        return () -> restTemplate.getForObject("http://api.tvmaze.com/singlesearch/shows?q=" + query, TvShowRemote.class);
    }

}
