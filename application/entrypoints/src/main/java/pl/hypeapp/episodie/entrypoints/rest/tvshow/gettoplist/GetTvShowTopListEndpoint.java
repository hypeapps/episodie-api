package pl.hypeapp.episodie.entrypoints.rest.tvshow.gettoplist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.core.usecase.tvshow.toplist.gettoplist.GetTvShowTopListUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetTvShowTopListEndpoint {

    private static final String API_PATH = "api/tvshow/toplist";

    private final GetTvShowTopListUseCase getTvShowTopListUseCase;

    public GetTvShowTopListEndpoint(GetTvShowTopListUseCase getTvShowTopListUseCase) {
        this.getTvShowTopListUseCase = getTvShowTopListUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public Page<TvShowDto> getTopList(Pageable pageableRequest) {
        Page<TvShowLocal> tvShowTopList;
        try {
            tvShowTopList = getTvShowTopListUseCase.getTopList(pageableRequest);
            return toDto(tvShowTopList, pageableRequest);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    private Page<TvShowDto> toDto(Page<TvShowLocal> tvShowTopList, Pageable pageableRequest) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        List<TvShowDto> tvShowDtos = tvShowTopList.getContent().stream()
                .map(objectMapper.tvShowLocalToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(tvShowDtos, pageableRequest, tvShowTopList.getTotalElements());
    }

}
