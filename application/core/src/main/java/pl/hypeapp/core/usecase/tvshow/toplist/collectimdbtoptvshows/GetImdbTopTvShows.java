package pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows;

import java.util.List;

public interface GetImdbTopTvShows {

    List<String> crawl(String url);

}
