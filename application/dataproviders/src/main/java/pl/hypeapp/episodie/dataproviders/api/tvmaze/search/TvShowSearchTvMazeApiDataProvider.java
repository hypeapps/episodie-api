package pl.hypeapp.episodie.dataproviders.api.tvmaze.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.usecase.tvshow.search.SearchTvShowInApi;

import java.util.Optional;

public class TvShowSearchTvMazeApiDataProvider implements SearchTvShowInApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowSearchTvMazeApiDataProvider.class);

    private final RestTemplate restTemplate;

    public TvShowSearchTvMazeApiDataProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<TvMazeId> search(String query) {
        try {
            TvShowRemote tvShowRemote = searchForTvShow(query);
            return Optional.of(new TvMazeId(tvShowRemote.getTvShowApiId()));
        } catch (RestClientException e) {
            LOGGER.info(e.getMessage());
        }
        return Optional.empty();
    }

    private TvShowRemote searchForTvShow(String query) {
        return restTemplate.getForObject("http://api.tvmaze.com/singlesearch/shows?q=" + query, TvShowRemote.class);
    }

}
