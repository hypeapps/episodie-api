package pl.hypeapp.episodie.dataproviders.database.mostpopular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.core.entity.database.TvShowMostPopularLocal;
import pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.InsertTvShowToMostPopular;

public class TvShowMostPopularDatabaseProvider implements InsertTvShowToMostPopular {

    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowMostPopularDatabaseProvider.class);

    private final TvShowMostPopularRepository tvShowMostPopularRepository;

    public TvShowMostPopularDatabaseProvider(TvShowMostPopularRepository tvShowMostPopularRepository) {
        this.tvShowMostPopularRepository = tvShowMostPopularRepository;
    }

    @Override
    public void insert(TvShowMostPopularLocal tvShowMostPopularLocal) {
        tvShowMostPopularRepository.save(tvShowMostPopularLocal);
        LOGGER.info("Inserted to most popular: " + tvShowMostPopularLocal.getPosition() + " : " + tvShowMostPopularLocal.getTvShowApiId());
    }

}
