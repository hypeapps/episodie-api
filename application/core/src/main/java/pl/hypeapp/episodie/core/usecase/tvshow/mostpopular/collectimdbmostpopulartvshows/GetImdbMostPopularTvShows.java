package pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows;

import java.util.List;

@FunctionalInterface
public interface GetImdbMostPopularTvShows {

    List<String> crawl(String imdbUrl);

}
