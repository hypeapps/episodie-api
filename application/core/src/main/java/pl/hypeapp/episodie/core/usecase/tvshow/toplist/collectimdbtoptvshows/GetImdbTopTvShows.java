package pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows;

import java.util.List;

public interface GetImdbTopTvShows {

    List<String> crawl(String url);

}
