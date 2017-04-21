package pl.hypeapp.episodie.entrypoints.rest.tvshow;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.core.entity.api.tvmaze.EpisodeRemote;
import pl.hypeapp.core.usecase.tvshow.gettvshow.EpisodeNotFoundException;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetTvShowEndpoint {
    private static final String API_PATH = "api/tvshow/get/{tvmazeId}";
    private GetTvShowUseCase getTvShowUseCase;

    public GetTvShowEndpoint(GetTvShowUseCase getTvShowUseCase) {
        this.getTvShowUseCase = getTvShowUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public EpisodeDto getTvShow(@PathVariable String tvmazeId) {
        try {
            EpisodeRemote episode = getTvShowUseCase.getEpisode(tvmazeId);
            return toDto(episode);
        } catch (EpisodeNotFoundException exception) {
            throw new NotFoundException();
        }
    }

    private EpisodeDto toDto(EpisodeRemote episode) {
        EpisodeDto episodeDto = new EpisodeDto();
//        episodeDto.setTvmazeId(tvshow.getTvmazeId());
        return episodeDto;
    }
}
