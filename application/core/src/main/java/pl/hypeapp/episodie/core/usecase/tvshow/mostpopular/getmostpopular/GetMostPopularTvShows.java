package pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;

import java.util.List;

public interface GetMostPopularTvShows {

    Page<TvShowMostPopularLocal> getMostPopular(Pageable pageableRequest);

    List<TvShowLocal> getMostPopularTvShows(List<String> topListIds);

}
