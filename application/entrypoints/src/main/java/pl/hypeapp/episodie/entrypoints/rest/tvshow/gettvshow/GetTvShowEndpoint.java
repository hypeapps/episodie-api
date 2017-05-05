package pl.hypeapp.episodie.entrypoints.rest.tvshow.gettvshow;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.core.usecase.tvshow.gettvshow.TvShowNotFoundException;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetTvShowEndpoint {

    private static final String API_PATH = "api/tvshow/get/{tvMazeId}";

    private GetTvShowUseCase getTvShowUseCase;

    public GetTvShowEndpoint(GetTvShowUseCase getTvShowUseCase) {
        this.getTvShowUseCase = getTvShowUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public TvShowDto getTvShow(@PathVariable String tvMazeId) {
        try {
            TvShowLocal tvShow = getTvShowUseCase.getTvShow(tvMazeId);
            return toDto(tvShow);
        } catch (TvShowNotFoundException exception) {
            throw new NotFoundException();
        }
    }

    private TvShowDto toDto(TvShowLocal tvShow) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        return objectMapper.tvShowLocalToDto.apply(tvShow);
    }

}
