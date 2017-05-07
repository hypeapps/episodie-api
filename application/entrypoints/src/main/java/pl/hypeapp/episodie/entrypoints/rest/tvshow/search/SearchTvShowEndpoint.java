package pl.hypeapp.episodie.entrypoints.rest.tvshow.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.core.usecase.tvshow.search.SearchTvShowUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;

import java.util.List;

@RestController
public class SearchTvShowEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTvShowEndpoint.class);

    private static final String API_PATH = "api/tvshow/search";

    private final SearchTvShowUseCase searchTvShowUseCase;

    public SearchTvShowEndpoint(SearchTvShowUseCase searchTvShowUseCase) {
        this.searchTvShowUseCase = searchTvShowUseCase;
    }

    @RequestMapping(value = API_PATH)
    public List<TvShowDto> search(String query) {
        searchTvShowUseCase.search(query);
        return null;
    }

}
