package pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows;

import org.junit.Test;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowTopListLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CollectImdbTopListUseCaseTest {

    private static final String URL = "http://www.example.com";

    private GetImdbTopTvShows getImdbTopTvShows = mock(GetImdbTopTvShows.class);

    private GetTvShowFromApi getTvShowFromApi = mock(GetTvShowFromApi.class);

    private GetTvShowIdFromApi getTvShowIdFromApi = mock(GetTvShowIdFromApi.class);

    private InsertTvShowToDatabase insertTvShowToDatabase = mock(InsertTvShowToDatabase.class);

    private InsertTvShowToTopList insertTvShowToTopList = mock(InsertTvShowToTopList.class);

    private CollectImdbTopListUseCase collectImdbTopListUseCase =
            new CollectImdbTopListUseCase(getImdbTopTvShows, getTvShowFromApi, getTvShowIdFromApi,
                    insertTvShowToTopList, insertTvShowToDatabase);

    @Test
    public void shouldGetTopTvShowsAndInsertToDataProvider() throws Exception {
        List<String> imdbIds = new LinkedList<>();
        imdbIds.add("1");
        imdbIds.add("1");
        Optional<TvMazeId> tvMazeId = Optional.of(new TvMazeId("1"));
        Optional<TvShowRemote> tvShowEntity = Optional.of(new TvShowRemote());
        Optional<TvShowLocal> tvShowLocal = Optional.of(new TvShowLocal());

        when(getImdbTopTvShows.crawl(URL)).thenReturn(imdbIds);
        when(getTvShowIdFromApi.getTvShowIdByImdbId("1")).thenReturn(tvMazeId);
        when(getTvShowFromApi.getTvShowByMazeId(tvMazeId.get())).thenReturn(tvShowEntity);
        when(insertTvShowToDatabase.insertTvShow(tvShowEntity.get())).thenReturn(tvShowLocal);

        collectImdbTopListUseCase.collect(URL);

        verify(getImdbTopTvShows, times(1)).crawl(URL);
        verify(getTvShowIdFromApi, times(imdbIds.size())).getTvShowIdByImdbId(imdbIds.get(0));
        verify(getTvShowFromApi, times(imdbIds.size())).getTvShowByMazeId(tvMazeId.get());
        verify(insertTvShowToDatabase, times(imdbIds.size())).insertTvShow(tvShowEntity.get());
        verify(insertTvShowToTopList, times(1)).insert(new TvShowTopListLocal(1, null));
    }

}