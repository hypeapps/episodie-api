package pl.hypeapp.episodie.entrypoints.rest.tvshow.search;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.search.SearchTvShowUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowExtendedDto;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchTvShowEndpoint {

    private static final String API_PATH = "api/tvshow/search";

    private static final String API_PATH_EXTENDED = "api/tvshow/search/extended";

    private final SearchTvShowUseCase searchTvShowUseCase;

    public SearchTvShowEndpoint(SearchTvShowUseCase searchTvShowUseCase) {
        this.searchTvShowUseCase = searchTvShowUseCase;
    }

    @RequestMapping(value = API_PATH)
    public List<TvShowDto> search(String query) {
        try {
            List<TvShowLocal> tvShowSearchResults = searchTvShowUseCase.search(query);
            return toDto(tvShowSearchResults);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = API_PATH_EXTENDED)
    public List<TvShowExtendedDto> searchExtended(String query) {
        try {
            List<TvShowLocal> tvShowSearchResults = searchTvShowUseCase.search(query);
            return toDtoExtended(tvShowSearchResults);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    private List<TvShowDto> toDto(List<TvShowLocal> tvShows) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        return tvShows.stream()
                .map(objectMapper.tvShowLocalToDto)
                .collect(Collectors.toList());
    }

    private List<TvShowExtendedDto> toDtoExtended(List<TvShowLocal> tvShows) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        return tvShows.stream()
                .map(objectMapper.tvShowLocalToDtoExtended)
                .collect(Collectors.toList());
    }

}
