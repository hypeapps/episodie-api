package pl.hypeapp.episodie.entrypoints.rest.tvshow.gettvshow;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.TvShowNotFoundException;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowExtendedDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.mapper.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetTvShowEndpoint {

    private static final String API_PATH = "api/tvshow/get/{tvMazeId}";

    private static final String API_PATH_EXTENDED = "api/tvshow/extended/get/{tvMazeId}";

    private GetTvShowUseCase getTvShowUseCase;

    public GetTvShowEndpoint(GetTvShowUseCase getTvShowUseCase) {
        this.getTvShowUseCase = getTvShowUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public TvShowDto getTvShow(@PathVariable String tvMazeId) {
        try {
            TvShowLocal tvShow = getTvShowUseCase.getTvShowFromLocal(tvMazeId);
            return toDto(tvShow);
        } catch (TvShowNotFoundException exception) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = API_PATH_EXTENDED, method = GET)
    public TvShowExtendedDto getTvShowExtended(@PathVariable String tvMazeId, @RequestParam("afterPremiereDate") Boolean afterPremiereDate) {
        try {
            TvShowLocal tvShow = getTvShowUseCase.getTvShowFromLocal(tvMazeId);
            return toDtoExtended(tvShow, afterPremiereDate);
        } catch (TvShowNotFoundException exception) {
            throw new NotFoundException();
        }
    }

    private TvShowDto toDto(TvShowLocal tvShow) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        return objectMapper.tvShowLocalToDto.apply(tvShow);
    }

    private TvShowExtendedDto toDtoExtended(TvShowLocal tvShow, boolean afterPremiereDate) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        if (afterPremiereDate) {
            return objectMapper.tvShowLocalToDtoExtendedAfterPremiereDate.apply(tvShow);
        } else {
            return objectMapper.tvShowLocalToDtoExtended.apply(tvShow);
        }
    }

}
