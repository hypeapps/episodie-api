package pl.hypeapp.episodie.dataproviders.database.tvshow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import pl.hypeapp.episodie.core.entity.TvShowEntity;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.episodie.core.entity.database.TvShowDatabaseAdapter;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowsUpdatesLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public class TvShowDatabaseProvider implements GetTvShowFromDatabase, InsertTvShowToDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowDatabaseProvider.class);

    private static final int COLUMN_TV_SHOW_API_ID = 0;

    private static final int COLUMN_UPDATED = 1;

    private final TvShowRepository tvShowRepository;

    public TvShowDatabaseProvider(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public boolean doesTvShowExist(String tvMazeId) {
        return tvShowRepository.doesTvShowExist(tvMazeId);
    }

    @Override
    public TvShowLocal getTvShow(String tvMazeId) {
        return tvShowRepository.findOne(tvMazeId);
    }

    @Override
    public TvShowsUpdatesLocal getUpdates() {
        List<Object[]> list = tvShowRepository.getUpdates();
        Map<String, Integer> updates = new HashMap<>();
        for (Object[] row : list) {
            updates.put((String) row[COLUMN_TV_SHOW_API_ID], (Integer) row[COLUMN_UPDATED]);
        }
        return new TvShowsUpdatesLocal(updates);
    }

    @Override
    public Optional<TvShowLocal> insertTvShow(TvShowEntity tvShowRemote) {
        try {
            TvShowLocal tvShowLocal = convertToLocalEntity(tvShowRemote);
            tvShowRepository.save(tvShowLocal);
            LOGGER.info("Saved tv show for id: " + tvShowLocal.getTvShowApiId());
            return Optional.of(tvShowLocal);
        } catch (Exception e) {
            LOGGER.info("Unable to save tv show for id: " + tvShowRemote.getTvShowApiId() + " because: " + e.getMessage());
        }
        return Optional.empty();
    }

    private TvShowLocal convertToLocalEntity(TvShowEntity tvShowEntity) {
        TvShowDatabaseAdapter tvShowDatabaseAdapter = new TvShowDatabaseAdapter((TvShowRemote) tvShowEntity);
        return tvShowDatabaseAdapter.apply();
    }

}
