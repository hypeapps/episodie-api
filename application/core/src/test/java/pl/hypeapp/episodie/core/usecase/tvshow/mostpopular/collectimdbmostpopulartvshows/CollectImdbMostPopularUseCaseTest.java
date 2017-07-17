package pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows;

import org.junit.Test;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CollectImdbMostPopularUseCaseTest {

    private static final String URL = "http://www.example.com";

    private GetImdbMostPopularTvShows getImdbMostPopularTvShows = mock(GetImdbMostPopularTvShows.class);

    private GetTvShowFromApi getTvShowFromApi = mock(GetTvShowFromApi.class);

    private GetTvShowIdFromApi getTvShowIdFromApi = mock(GetTvShowIdFromApi.class);

    private InsertTvShowToDatabase insertTvShowToDatabase = mock(InsertTvShowToDatabase.class);

    private InsertTvShowToMostPopular insertTvShowToMostPopular = mock(InsertTvShowToMostPopular.class);

    private CollectImdbMostPopularUseCase collectImdbMostPopularUseCase =
            new CollectImdbMostPopularUseCase(getImdbMostPopularTvShows, getTvShowFromApi, getTvShowIdFromApi,
                    insertTvShowToMostPopular, insertTvShowToDatabase);

    @Test
    public void shouldGetTopTvShowsAndInsertToDataProvider() throws Exception {
        List<String> imdbIds = new LinkedList<>();
        imdbIds.add("1");
        imdbIds.add("1");
        Optional<TvMazeId> tvMazeId = Optional.of(new TvMazeId("1"));
        Optional<TvShowRemote> tvShowEntity = Optional.of(new TvShowRemote());
        Optional<TvShowLocal> tvShowLocal = Optional.of(new TvShowLocal());

        when(getImdbMostPopularTvShows.crawl(URL)).thenReturn(imdbIds);
        when(getTvShowIdFromApi.getTvShowIdByImdbId("1")).thenReturn(tvMazeId);
        when(getTvShowFromApi.getTvShowByMazeId(tvMazeId.get())).thenReturn(tvShowEntity);
        when(insertTvShowToDatabase.insertTvShow(tvShowEntity.get())).thenReturn(tvShowLocal);

        collectImdbMostPopularUseCase.collect(URL);

        verify(getImdbMostPopularTvShows, times(1)).crawl(URL);
        verify(getTvShowIdFromApi, times(imdbIds.size())).getTvShowIdByImdbId(imdbIds.get(0));
        verify(getTvShowFromApi, times(imdbIds.size())).getTvShowByMazeId(tvMazeId.get());
        verify(insertTvShowToDatabase, times(imdbIds.size())).insertTvShow(tvShowEntity.get());
        verify(insertTvShowToMostPopular, times(1)).insert(new TvShowMostPopularLocal(1, null));
    }

}