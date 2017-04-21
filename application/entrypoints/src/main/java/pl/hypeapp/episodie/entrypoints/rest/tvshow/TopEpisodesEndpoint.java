package pl.hypeapp.episodie.entrypoints.rest.tvshow;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.core.entity.api.tvmaze.EpisodeRemote;
import pl.hypeapp.core.usecase.tvshow.toplist.gettoplist.GetTop100UseCase;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TopEpisodesEndpoint {
    private static final String API_PATH = "api/tvshow/top100/";
    private final GetTop100UseCase getTop100UseCase;

    public TopEpisodesEndpoint(GetTop100UseCase getTop100UseCase) {
        this.getTop100UseCase = getTop100UseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public List<EpisodeRemote> getTop100() {
        try {
//            List<EpisodeRemote> top100Episodes = getTop100UseCase.getTop100();
            List<EpisodeRemote> top100Episodes = null;
            return top100Episodes;
        } catch (NotFoundException ex) {
            throw new NotFoundException();
        }
    }
}
