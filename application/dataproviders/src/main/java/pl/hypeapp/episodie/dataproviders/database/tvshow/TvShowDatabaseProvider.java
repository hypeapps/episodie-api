package pl.hypeapp.episodie.dataproviders.database.tvshow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import pl.hypeapp.core.entity.TvShowEntity;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.entity.database.TvShowDatabaseAdapter;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowFromDatabase;

import java.util.Optional;

@Transactional
public class TvShowDatabaseProvider implements GetTvShowFromDatabase, InsertTvShowToDatabase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowDatabaseProvider.class);
    private final TvShowRepository tvShowRepository;

    public TvShowDatabaseProvider(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public boolean doesTvShowExist(String tvMazeId) {
        return true;
    }

    @Override
    public String getTvShow(String tvMazeId) {
        return null;
    }

    @Override
    public Optional<TvShowLocal> insertTvShow(TvShowEntity tvShowRemote) {
        try {
            TvShowLocal tvShowLocal = convertToDao(tvShowRemote);
            tvShowRepository.save(tvShowLocal);
            LOGGER.info("Saved tv show for id: " + tvShowLocal.getTvShowApiId());
            return Optional.of(tvShowLocal);
        } catch (Exception e) {
            LOGGER.info(tvShowRemote.getTvShowApiId() + " " + e.getMessage());
        }
        return Optional.empty();
    }

    private TvShowLocal convertToDao(TvShowEntity tvShowEntity) {
        TvShowDatabaseAdapter tvShowDatabaseAdapter = new TvShowDatabaseAdapter((TvShowRemote) tvShowEntity);
        return tvShowDatabaseAdapter.apply();
    }

}
