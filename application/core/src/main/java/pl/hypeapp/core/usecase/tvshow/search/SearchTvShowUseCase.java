package pl.hypeapp.core.usecase.tvshow.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.core.usecase.tvshow.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchTvShowUseCase {

    private static final int SEARCH_EMPTY_RESULTS = 0;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTvShowUseCase.class);

    private final SearchTvShow searchTvShow;

    private final SearchTvShowInApi searchTvShowInApi;

    private final GetTvShowFromApi getTvShowFromApi;

    private final InsertTvShowToDatabase insertTvShowToDatabase;

    public SearchTvShowUseCase(SearchTvShow searchTvShow, SearchTvShowInApi searchTvShowInApi, GetTvShowFromApi getTvShowFromApi,
                               InsertTvShowToDatabase insertTvShowToDatabase) {
        this.searchTvShow = searchTvShow;
        this.searchTvShowInApi = searchTvShowInApi;
        this.getTvShowFromApi = getTvShowFromApi;
        this.insertTvShowToDatabase = insertTvShowToDatabase;
    }

    public List<TvShowLocal> search(String query) {
        if (query.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        List<TvShowLocal> searchResults = new ArrayList<>();
        List<TvShowLocal> searchDatabaseResults = searchInDatabase(query);
        searchResults.addAll(searchDatabaseResults);
        Optional<TvMazeId> tvMazeId = searchTvShowInApi.search(query);
        if (tvMazeId.isPresent() && !isTvShowContainsAlready(searchDatabaseResults, tvMazeId.get())) {
            try {
                TvShowRemote tvShowRemote = getTvShowFromApi(tvMazeId.get());
                TvShowLocal tvShowInserted = insertTvShowToDataProvider(tvShowRemote);
                searchResults.add(tvShowInserted);
            } catch (ResourceNotFoundException e) {
                LOGGER.info("INSERT SEARCH RESULT FROM API FAILED: " + e.getMessage());
            }
        }
        if (isEmptyResults(searchResults)) {
            throw new ResourceNotFoundException();
        }
        return searchResults;
    }

    private List<TvShowLocal> searchInDatabase(String query) {
        return searchTvShow.search(query);
    }

    private TvShowRemote getTvShowFromApi(TvMazeId tvMazeId) {
        Optional<TvShowRemote> tvShowRemote = getTvShowFromApi.getTvShowByMazeId(tvMazeId);
        if (!tvShowRemote.isPresent()) {
            LOGGER.info("GET TV SHOW FORM API IS NOT PRESENT");
            throw new ResourceNotFoundException();
        }
        return tvShowRemote.get();
    }

    private TvShowLocal insertTvShowToDataProvider(TvShowRemote tvShowRemote) {
        Optional<TvShowLocal> tvShowInserted = insertTvShowToDatabase.insertTvShow(tvShowRemote);
        if (!tvShowInserted.isPresent()) {
            LOGGER.info("NOT ABLE TO INSERT TV SHOW");
            throw new ResourceNotFoundException();
        }
        return tvShowInserted.get();
    }

    private boolean isTvShowContainsAlready(List<TvShowLocal> searchResults, TvMazeId tvMazeId) {
        return searchResults.stream().anyMatch(tvShowLocal -> tvShowLocal.getTvShowApiId().equals(tvMazeId.getId()));
    }

    private boolean isEmptyResults(List<TvShowLocal> searchResults) {
        return searchResults.size() == SEARCH_EMPTY_RESULTS;
    }

}
