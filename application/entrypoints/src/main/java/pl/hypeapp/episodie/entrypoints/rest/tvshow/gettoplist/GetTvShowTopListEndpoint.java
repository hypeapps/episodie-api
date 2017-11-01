package pl.hypeapp.episodie.entrypoints.rest.tvshow.gettoplist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.gettoplist.GetTopListUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowExtendedDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.mapper.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetTvShowTopListEndpoint {

    private static final String API_PATH = "api/tvshow/toplist";

    private static final String API_PATH_EXTENDED = "api/tvshow/extended/toplist";

    private final GetTopListUseCase getTopListUseCase;

    public GetTvShowTopListEndpoint(GetTopListUseCase getTopListUseCase) {
        this.getTopListUseCase = getTopListUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public Page<TvShowDto> getTopList(Pageable pageableRequest) {
        Page<TvShowLocal> tvShowTopList;
        try {
            tvShowTopList = getTopListUseCase.getTopList(pageableRequest);
            return toDto(tvShowTopList, pageableRequest);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = API_PATH_EXTENDED, method = GET)
    public Page<TvShowExtendedDto> getTopListExtended(Pageable pageableRequest) {
        Page<TvShowLocal> tvShowTopList;
        try {
            tvShowTopList = getTopListUseCase.getTopList(pageableRequest);
            return toDtoExtended(tvShowTopList, pageableRequest);
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

    private Page<TvShowExtendedDto> toDtoExtended(Page<TvShowLocal> tvShowTopList, Pageable pageableRequest) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        List<TvShowExtendedDto> tvShowExtendedDtos = tvShowTopList.getContent().stream()
                .map(objectMapper.tvShowLocalToDtoExtendedAfterPremiereDate)
                .collect(Collectors.toList());
        return new PageImpl<>(tvShowExtendedDtos, pageableRequest, tvShowTopList.getTotalElements());
    }

}
