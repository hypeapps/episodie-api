package pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows;

import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;

@FunctionalInterface
public interface InsertTvShowToMostPopular {

    void insert(TvShowMostPopularLocal tvShowMostPopularLocal);

}
