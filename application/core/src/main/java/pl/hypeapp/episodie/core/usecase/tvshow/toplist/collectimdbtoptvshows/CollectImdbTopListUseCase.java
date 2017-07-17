package pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.episodie.core.entity.TvShowEntity;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowTopListLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectImdbTopListUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectImdbTopListUseCase.class);

    private final GetImdbTopTvShows getImdbTopTvShows;

    private final GetTvShowFromApi getTvShowFromApi;

    private final GetTvShowIdFromApi getTvShowIdFromApi;

    private final InsertTvShowToTopList insertTvShowToTopList;

    private final InsertTvShowToDatabase insertTvShowToDatabase;

    public CollectImdbTopListUseCase(GetImdbTopTvShows getImdbTopTvShows, GetTvShowFromApi getTvShowFromApi,
                                     GetTvShowIdFromApi getTvShowIdFromApi, InsertTvShowToTopList insertTvShowToTopList,
                                     InsertTvShowToDatabase insertTvShowToDatabase) {
        this.getImdbTopTvShows = getImdbTopTvShows;
        this.getTvShowFromApi = getTvShowFromApi;
        this.getTvShowIdFromApi = getTvShowIdFromApi;
        this.insertTvShowToTopList = insertTvShowToTopList;
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

        insertTvShowsToTopList(tvShowsLocalInserted);
    }

    private List<String> getImdbIds(String url) {
        try {
            return getImdbTopTvShows.crawl(url);
        } catch (ImdbTopTvShowCrawlerFailException e) {
            LOGGER.info("Crawler Failed" + e.getMessage());
            throw new CollectImdbTopTvShowsException();
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

    private void insertTvShowsToTopList(List<TvShowLocal> tvShowsLocalInserted) {
        for (int i = 0; i < tvShowsLocalInserted.size(); i++) {
            insertTvShowToTopList.insert(new TvShowTopListLocal(i + 1, tvShowsLocalInserted.get(i).getTvShowApiId()));
        }
    }

}
