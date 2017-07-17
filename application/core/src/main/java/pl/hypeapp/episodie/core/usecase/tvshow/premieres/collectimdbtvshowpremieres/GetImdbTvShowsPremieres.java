package pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectimdbtvshowpremieres;

import pl.hypeapp.episodie.core.entity.crawler.ImdbPremiere;

import java.util.List;

public interface GetImdbTvShowsPremieres {

    List<ImdbPremiere> crawl();

}
