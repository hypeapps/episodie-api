package pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.episodie.core.entity.TvShowEntity;
import pl.hypeapp.episodie.core.entity.api.tvmaze.EpisodePremiereRemote;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.crawler.ImdbPremiere;
import pl.hypeapp.episodie.core.entity.database.EpisodePremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowPremiereLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetPremieresUseCase;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectPremieresUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectPremieresUseCase.class);

    private final GetImdbTvShowsPremieres getImdbTvShowsPremieres;

    private final GetTvShowIdFromApi getTvShowIdFromApi;

    private final GetTvShowFromApi getTvShowFromApi;

    private final GetPremieresUseCase getPremieresUseCase;

    private final InsertTvShowToDatabase insertTvShowToDatabase;

    private final InsertPremieres insertPremieres;

    public CollectPremieresUseCase(GetImdbTvShowsPremieres getImdbTvShowsPremieres,
                                   GetTvShowIdFromApi getTvShowIdFromApi,
                                   GetTvShowFromApi getTvShowFromApi,
                                   GetPremieresUseCase getPremieresUseCase, InsertTvShowToDatabase insertTvShowToDatabase,
                                   InsertPremieres insertPremieres) {
        this.getImdbTvShowsPremieres = getImdbTvShowsPremieres;
        this.getTvShowIdFromApi = getTvShowIdFromApi;
        this.getTvShowFromApi = getTvShowFromApi;
        this.getPremieresUseCase = getPremieresUseCase;
        this.insertTvShowToDatabase = insertTvShowToDatabase;
        this.insertPremieres = insertPremieres;
    }

    public void collect() {
        collectEpisodePremieres();
        collectImdbPremieres();
    }

    private void collectImdbPremieres() {
        List<ImdbPremiere> imdbPremieres = getImdbIds();
        LOGGER.info("IMDB TV PREMIERES SIZE: " + imdbPremieres.size());

        List<TvMazeId> tvMazeIds = getTvMazeIds(imdbPremieres);
        LOGGER.info("TV PREMIERES SIZE: " + tvMazeIds.size());

        List<TvShowEntity> tvShows = getTvShows(tvMazeIds);
        LOGGER.info("TV SHOW ENTITES SIZE: " + tvShows.size());

        List<TvShowLocal> tvShowsLocalInserted = insertTvShowsToDataProvider(tvShows);
        LOGGER.info("TV SHOW LOCAL INSERTED: " + tvShowsLocalInserted.size());

        insertTvShowPremieres(tvShowsLocalInserted, imdbPremieres);
    }

    private void collectEpisodePremieres() {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (int i = 1; i <= 21; i++) {
            List<EpisodePremiereRemote> episodePremieresRemote = getPremieresUseCase.getEpisodePremieresRemote(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
            List<TvShowEntity> tvShows = getTvShows(episodePremieresRemote.stream()
                    .map(s -> new TvMazeId(s.getShow().getTvShowId()))
                    .collect(Collectors.toList()));
            LOGGER.info("TV SHOW ENTITES SIZE: " + tvShows.size());
            tvShows.forEach(tvShowEntity -> {
                LOGGER.info(tvShowEntity.getTvShowApiId());
            });
            List<TvShowLocal> tvShowsLocalInserted = insertTvShowsToDataProvider(tvShows);
            LOGGER.info("TV SHOW LOCAL INSERTED: " + tvShowsLocalInserted.size());
            insertEpisodePremieres(episodePremieresRemote);
            localDateTime = localDateTime.plusDays(1);
        }
    }

    private List<ImdbPremiere> getImdbIds() {
        try {
            return getImdbTvShowsPremieres.crawl();
        } catch (ImdbPremieresTvShowCrawlerFailException e) {
            LOGGER.info("Crawler Failed " + e.getMessage());
            throw new CollectImdbTvShowsPremieresException();
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

    private void insertTvShowPremieres(List<TvShowLocal> tvShowsLocalInserted, List<ImdbPremiere> imdbPremieres) {
        List<TvShowPremiereLocal> premiersToInsert = new ArrayList<>();
        tvShowsLocalInserted.forEach(tvShowLocal -> {
            for (ImdbPremiere imdbPremiere : imdbPremieres) {
                if (imdbPremiere.getImdbId().equals(tvShowLocal.getImdbId())) {
                    premiersToInsert.add(new TvShowPremiereLocal(tvShowLocal.getTvShowApiId(), Date.from(imdbPremiere.getPremiereDate()
                            .atStartOfDay(ZoneId.systemDefault()).toInstant())));
                }
            }
        });
        insertPremieres.insert(premiersToInsert);
    }

    private void insertEpisodePremieres(List<EpisodePremiereRemote> episodePremieres) {
        episodePremieres.forEach(episodePremiere -> insertPremieres.insertEpisodePremiere
                (new EpisodePremiereLocal(episodePremiere.getShow().getTvShowId(),
                        episodePremiere.getEpisodeId(),
                        episodePremiere.getAirstamp())));
    }

}
