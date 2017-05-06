package pl.hypeapp.core.usecase.tvshow.update;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.entity.api.tvmaze.TvShowsUpdatesRemote;
import pl.hypeapp.core.entity.database.TvShowsUpdatesLocal;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromDatabase;
import pl.hypeapp.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.*;

public class UpdateTvShowsUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTvShowsUseCase.class);
    private final GetTvShowFromApi getTvShowFromApi;
    private final GetTvShowFromDatabase getTvShowFromDatabase;
    private final GetTvShowsUpdates getTvShowsUpdates;
    private final InsertTvShowToDatabase insertTvShowToDatabase;

    public UpdateTvShowsUseCase(GetTvShowFromApi getTvShowFromApi, GetTvShowFromDatabase getTvShowFromDatabase,
                                GetTvShowsUpdates getTvShowsUpdates, InsertTvShowToDatabase insertTvShowToDatabase) {
        this.getTvShowFromApi = getTvShowFromApi;
        this.getTvShowFromDatabase = getTvShowFromDatabase;
        this.getTvShowsUpdates = getTvShowsUpdates;
        this.insertTvShowToDatabase = insertTvShowToDatabase;
    }

    public void update() {
        try {
            TvShowsUpdatesRemote tvShowsUpdatesRemote = getTvShowsUpdates.getUpdates().get();
            TvShowsUpdatesLocal tvShowsUpdatesLocal = getTvShowFromDatabase.getUpdates();
            LOGGER.info("TV SHOWS TO UPDATE " + getTvShowsToUpdate(tvShowsUpdatesRemote, tvShowsUpdatesLocal).size());
            Map<String, Integer> tvShowsToUpdate = getTvShowsToUpdate(tvShowsUpdatesRemote, tvShowsUpdatesLocal);
            List<TvShowRemote> updatedTvShows = getUpdatedTvShows(tvShowsToUpdate);
            insertUpdatedTvShowsToDataProvider(updatedTvShows);
        } catch (Exception e) {
            throw new UpdateTvShowsException();
        }
    }

    private Map<String, Integer> getTvShowsToUpdate(TvShowsUpdatesRemote tvShowsUpdatesRemote, TvShowsUpdatesLocal tvShowsUpdatesLocal) {
        Map<String, Integer> tvShowsToUpdate = new HashMap<>();
        MapDifference<String, Integer> mapDifference = Maps.difference(tvShowsUpdatesRemote.getUpdates(), tvShowsUpdatesLocal.getUpdates());
        mapDifference.entriesDiffering().forEach((tvMazeId, timestamps) -> tvShowsToUpdate.put(tvMazeId, timestamps.leftValue()));
        return tvShowsToUpdate;
    }

    private List<TvShowRemote> getUpdatedTvShows(Map<String, Integer> tvShowsToUpdate) {
        List<TvShowRemote> updatedTvShows = new LinkedList<>();
        tvShowsToUpdate.forEach((tvMazeId, timestamp) -> {
            Optional<TvShowRemote> tvShowRemoteOptional = getTvShowFromApi.getTvShowByMazeId(new TvMazeId(tvMazeId));
            tvShowRemoteOptional.ifPresent(updatedTvShows::add);
        });
        return updatedTvShows;
    }

    private void insertUpdatedTvShowsToDataProvider(List<TvShowRemote> updatedTvShows) {
        updatedTvShows.forEach(insertTvShowToDatabase::insertTvShow);
    }

}
