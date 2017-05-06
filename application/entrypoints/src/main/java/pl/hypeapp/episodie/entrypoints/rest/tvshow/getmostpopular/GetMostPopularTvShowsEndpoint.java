package pl.hypeapp.episodie.entrypoints.rest.tvshow.getmostpopular;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetMostPopularTvShowsEndpoint {

    private static final String API_PATH = "api/tvshow/mostpopular";

    private final GetMostPopularTvShowsUseCase getMostPopularTvShowsUseCase;

    public GetMostPopularTvShowsEndpoint(GetMostPopularTvShowsUseCase getMostPopularTvShowsUseCase) {
        this.getMostPopularTvShowsUseCase = getMostPopularTvShowsUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public Page<TvShowLocal> getMostPopular(Pageable pageableRequest) {
        Page<TvShowLocal> mostPopularTvShows;
        try {
            mostPopularTvShows = getMostPopularTvShowsUseCase.getMostPopular(pageableRequest);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
        return mostPopularTvShows;
    }

}
