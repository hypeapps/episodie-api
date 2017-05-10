package pl.hypeapp.episodie.entrypoints.rest.tvshow.search;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.core.usecase.tvshow.search.SearchTvShowUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchTvShowEndpoint {

    private static final String API_PATH = "api/tvshow/search";

    private final SearchTvShowUseCase searchTvShowUseCase;

    public SearchTvShowEndpoint(SearchTvShowUseCase searchTvShowUseCase) {
        this.searchTvShowUseCase = searchTvShowUseCase;
    }

    @RequestMapping(value = API_PATH)
    public List<TvShowDto> search(String query) {
        try {
            List<TvShowLocal> tvShowSearchResults = searchTvShowUseCase.search(query);
            return toDtos(tvShowSearchResults);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    private List<TvShowDto> toDtos(List<TvShowLocal> tvShows) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        return tvShows.stream()
                .map(tvShow -> objectMapper.tvShowLocalToDto.apply(tvShow))
                .collect(Collectors.toList());
    }

}
