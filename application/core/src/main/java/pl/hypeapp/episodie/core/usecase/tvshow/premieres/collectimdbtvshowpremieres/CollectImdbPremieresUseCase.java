package pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectimdbtvshowpremieres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.episodie.core.entity.TvShowEntity;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.crawler.ImdbPremiere;
import pl.hypeapp.episodie.core.entity.database.PremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectImdbPremieresUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectImdbPremieresUseCase.class);

    private final GetImdbTvShowsPremieres getImdbTvShowsPremieres;

    private final GetTvShowIdFromApi getTvShowIdFromApi;

    private final GetTvShowFromApi getTvShowFromApi;

    private final InsertTvShowToDatabase insertTvShowToDatabase;

    private final InsertPremieres insertPremieres;

    public CollectImdbPremieresUseCase(GetImdbTvShowsPremieres getImdbTvShowsPremieres,
                                       GetTvShowIdFromApi getTvShowIdFromApi,
                                       GetTvShowFromApi getTvShowFromApi,
                                       InsertTvShowToDatabase insertTvShowToDatabase,
                                       InsertPremieres insertPremieres) {
        this.getImdbTvShowsPremieres = getImdbTvShowsPremieres;
        this.getTvShowIdFromApi = getTvShowIdFromApi;
        this.getTvShowFromApi = getTvShowFromApi;
        this.insertTvShowToDatabase = insertTvShowToDatabase;
        this.insertPremieres = insertPremieres;
    }

    public void collect() {
        List<ImdbPremiere> imdbPremieres = getImdbIds();
        imdbPremieres.forEach(s ->
                LOGGER.info("Imdb ids " + s));

        List<TvMazeId> tvMazeIds = getTvMazeIds(imdbPremieres);
        LOGGER.info("TV PREMIERES SIZE: " + tvMazeIds.size());

        List<TvShowEntity> tvShows = getTvShows(tvMazeIds);
        LOGGER.info("TV ENTITES SIZE: " + tvShows.size());

        List<TvShowLocal> tvShowsLocalInserted = insertTvShowsToDataProvider(tvShows);
        LOGGER.info("tv shows local size: " + tvShowsLocalInserted.size());

        insertPremieres(tvShowsLocalInserted, imdbPremieres);
    }

    private List<ImdbPremiere> getImdbIds() {
        try {
            return getImdbTvShowsPremieres.crawl();
        } catch (ImdbPremieresTvShowCrawlerFailException e) {
            LOGGER.info("Crawler Failed " + e.getMessage());
            throw new CollectImdbMostPopularTvShowsException();
        }
    }

    private List<TvMazeId> getTvMazeIds(List<ImdbPremiere> imdbPremieres) {
        return imdbPremieres.stream()
                .map(imdbPremiere -> getTvShowIdFromApi.getTvShowIdByImdbId(imdbPremiere.getImdbId()))
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

    private void insertPremieres(List<TvShowLocal> tvShowsLocalInserted, List<ImdbPremiere> imdbPremieres) {
        List<PremiereLocal> premiersToInsert = new ArrayList<>();
        tvShowsLocalInserted.forEach(tvShowLocal -> {
            for (ImdbPremiere imdbPremiere : imdbPremieres) {
                if (imdbPremiere.getImdbId().equals(tvShowLocal.getImdbId())) {
                    premiersToInsert.add(new PremiereLocal(tvShowLocal.getTvShowApiId(),
                            imdbPremiere.getPremiereDate().toString()));
                }
            }
        });
        insertPremieres.insert(premiersToInsert);
    }

}
