package pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows;

import java.util.List;

public interface GetImdbMostPopularTvShows {

    List<String> crawl(String imdbUrl);

}
