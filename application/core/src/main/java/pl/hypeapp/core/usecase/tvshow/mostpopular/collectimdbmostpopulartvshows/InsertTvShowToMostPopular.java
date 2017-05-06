package pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows;

import pl.hypeapp.core.entity.database.TvShowMostPopularLocal;

@FunctionalInterface
public interface InsertTvShowToMostPopular {

    void insert(TvShowMostPopularLocal tvShowMostPopularLocal);

}
