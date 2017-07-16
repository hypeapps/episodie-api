package pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular;

import com.google.common.collect.Ordering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class GetMostPopularUseCase {

    private final GetMostPopularTvShows getMostPopularTvShows;

    public GetMostPopularUseCase(GetMostPopularTvShows getMostPopularTvShows) {
        this.getMostPopularTvShows = getMostPopularTvShows;
    }

    public Page<TvShowLocal> getMostPopular(Pageable pageableRequest) {
        Page<TvShowMostPopularLocal> topList = getMostPopularTvShows.getMostPopular(pageableRequest);
        if (pageableRequest.getPageNumber() >= topList.getTotalPages()) {
            throw new ResourceNotFoundException();
        }
        List<String> mostPopularIds = getTopListIds(topList);
        List<TvShowLocal> tvShowTopList = getMostPopularTvShows.getMostPopularTvShows(mostPopularIds);
        orderTvShowsById(mostPopularIds, tvShowTopList);
        return new PageImpl<>(tvShowTopList, pageableRequest, topList.getTotalElements());
    }

    private List<String> getTopListIds(Page<TvShowMostPopularLocal> mostPopular) {
        return mostPopular.getContent().stream()
                .map(TvShowMostPopularLocal::getTvShowApiId)
                .collect(Collectors.toList());
    }

    private void orderTvShowsById(List<String> mostPopularIds, List<TvShowLocal> mostPopularTvShows) {
        Ordering<String> idOrdering = Ordering.explicit(mostPopularIds);
        mostPopularTvShows.sort((o1, o2) -> idOrdering.compare(o1.getTvShowApiId(), o2.getTvShowApiId()));
    }

}
