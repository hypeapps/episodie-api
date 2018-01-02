package pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres;

import pl.hypeapp.episodie.core.entity.crawler.ImdbPremiere;

import java.util.List;

public interface GetImdbTvShowsPremieres {

    List<ImdbPremiere> crawl();

}
