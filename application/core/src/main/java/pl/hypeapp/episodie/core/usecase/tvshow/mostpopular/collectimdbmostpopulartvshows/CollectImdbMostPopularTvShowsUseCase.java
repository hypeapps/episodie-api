package pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.episodie.core.entity.TvShowEntity;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectImdbMostPopularTvShowsUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectImdbMostPopularTvShowsUseCase.class);

    private final GetImdbMostPopularTvShows getImdbMostPopularTvShows;

    private final GetTvShowFromApi getTvShowFromApi;

    private final GetTvShowIdFromApi getTvShowIdFromApi;

    private final InsertTvShowToMostPopular insertTvShowToMostPopular;

    private final InsertTvShowToDatabase insertTvShowToDatabase;

    public CollectImdbMostPopularTvShowsUseCase(GetImdbMostPopularTvShows getImdbMostPopularTvShows, GetTvShowFromApi getTvShowFromApi,
                                                GetTvShowIdFromApi getTvShowIdFromApi, InsertTvShowToMostPopular insertTvShowToMostPopular,
                                                InsertTvShowToDatabase insertTvShowToDatabase) {
        this.getImdbMostPopularTvShows = getImdbMostPopularTvShows;
        this.getTvShowFromApi = getTvShowFromApi;
        this.getTvShowIdFromApi = getTvShowIdFromApi;
        this.insertTvShowToMostPopular = insertTvShowToMostPopular;
        this.insertTvShowToDatabase = insertTvShowToDatabase;
    }

    public void collect(String url) {
        List<String> imdbIds = getImdbIds(url);
        LOGGER.info("imdb ids size: " + imdbIds.size());

        List<TvMazeId> tvMazeIds = getTvMazeIds(imdbIds);
        LOGGER.info("tvmaze ids size: " + tvMazeIds.size());

        List<TvShowEntity> tvShows = getTvShows(tvMazeIds);
        LOGGER.info("tv shows size: " + tvShows.size());

        List<TvShowLocal> tvShowsLocalInserted = insertTvShowsToDataProvider(tvShows);
        LOGGER.info("tv shows local size: " + tvShowsLocalInserted.size());

        insertTvShowsToMostPopular(tvShowsLocalInserted);
    }

    private List<String> getImdbIds(String url) {
        try {
            return getImdbMostPopularTvShows.crawl(url);
        } catch (ImdbMostPopularTvShowCrawlerFailException e) {
            LOGGER.info("Crawler Failed " + e.getMessage());
            throw new CollectImdbMostPopularTvShowsException();
        }
    }

    private List<TvMazeId> getTvMazeIds(List<String> imdbIds) {
        return imdbIds.stream()
                .map(getTvShowIdFromApi::getTvShowIdByImdbId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<TvShowEntity> getTvShows(List<TvMazeId> tvMazeIds) {
        return tvMazeIds.stream()
                .map(getTvShowFromApi::getTvShowByMazeId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<TvShowLocal> insertTvShowsToDataProvider(List<TvShowEntity> tvShowEntities) {
        return tvShowEntities.stream()
                .map(insertTvShowToDatabase::insertTvShow)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private void insertTvShowsToMostPopular(List<TvShowLocal> tvShowsLocalInserted) {
        for (int i = 0; i < tvShowsLocalInserted.size(); i++) {
            insertTvShowToMostPopular.insert(new TvShowMostPopularLocal(i + 1, tvShowsLocalInserted.get(i).getTvShowApiId()));
        }
    }

}
