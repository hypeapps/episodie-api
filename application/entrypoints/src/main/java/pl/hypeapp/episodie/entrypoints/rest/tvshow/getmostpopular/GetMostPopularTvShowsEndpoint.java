package pl.hypeapp.episodie.entrypoints.rest.tvshow.getmostpopular;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowExtendedDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.mapper.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetMostPopularTvShowsEndpoint {

    private static final String API_PATH = "api/tvshow/mostpopular";

    private static final String API_PATH_EXTENDED = "api/tvshow/extended/mostpopular";

    private final GetMostPopularUseCase getMostPopularUseCase;

    public GetMostPopularTvShowsEndpoint(GetMostPopularUseCase getMostPopularUseCase) {
        this.getMostPopularUseCase = getMostPopularUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public Page<TvShowDto> getMostPopular(Pageable pageableRequest) {
        Page<TvShowLocal> mostPopularTvShows;
        try {
            mostPopularTvShows = getMostPopularUseCase.getMostPopular(pageableRequest);
            return toDto(mostPopularTvShows, pageableRequest);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = API_PATH_EXTENDED, method = GET)
    public Page<TvShowExtendedDto> getMostPopularExtended(Pageable pageableRequest) {
        Page<TvShowLocal> mostPopularTvShows;
        try {
            mostPopularTvShows = getMostPopularUseCase.getMostPopular(pageableRequest);
            return toExtendedDto(mostPopularTvShows, pageableRequest);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    private Page<TvShowDto> toDto(Page<TvShowLocal> mostPopularTvShows, Pageable pageableRequest) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        List<TvShowDto> tvShowDtos = mostPopularTvShows.getContent().stream()
                .map(objectMapper.tvShowLocalToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(tvShowDtos, pageableRequest, mostPopularTvShows.getTotalElements());
    }

    private Page<TvShowExtendedDto> toExtendedDto(Page<TvShowLocal> mostPopularTvShows, Pageable pageableRequest) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        List<TvShowExtendedDto> tvShowExtendedDtos = mostPopularTvShows.getContent().stream()
                .map(objectMapper.tvShowLocalToDtoExtendedAfterPremiereDate)
                .collect(Collectors.toList());
        return new PageImpl<>(tvShowExtendedDtos, pageableRequest, mostPopularTvShows.getTotalElements());
    }

}
