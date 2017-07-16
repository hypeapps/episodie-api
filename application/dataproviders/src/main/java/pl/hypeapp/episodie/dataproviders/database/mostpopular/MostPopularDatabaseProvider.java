package pl.hypeapp.episodie.dataproviders.database.mostpopular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.InsertTvShowToMostPopular;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularTvShows;

import java.util.List;

public class MostPopularDatabaseProvider implements InsertTvShowToMostPopular, GetMostPopularTvShows {

    private static final Logger LOGGER = LoggerFactory.getLogger(MostPopularDatabaseProvider.class);

    private final TvShowMostPopularRepository tvShowMostPopularRepository;

    public MostPopularDatabaseProvider(TvShowMostPopularRepository tvShowMostPopularRepository) {
        this.tvShowMostPopularRepository = tvShowMostPopularRepository;
    }

    @Override
    public void insert(TvShowMostPopularLocal tvShowMostPopularLocal) {
        tvShowMostPopularRepository.save(tvShowMostPopularLocal);
        LOGGER.info("Inserted to most popular: " + tvShowMostPopularLocal.getPosition() + " : " + tvShowMostPopularLocal.getTvShowApiId());
    }

    @Override
    public Page<TvShowMostPopularLocal> getMostPopular(Pageable pageableRequest) {
        return tvShowMostPopularRepository.findAll(pageableRequest);
    }

    @Override
    public List<TvShowLocal> getMostPopularTvShows(List<String> mostPopularIds) {
        return tvShowMostPopularRepository.getTopListTvShows(mostPopularIds);
    }

}
