package pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows;

import org.junit.Test;
import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.entity.database.TvShowTopListLocal;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CollectImdbTopTvShowsUseCaseTest {
    private static final String URL = "http://www.example.com";
    private GetImdbTopTvShows getImdbTopTvShows = mock(GetImdbTopTvShows.class);
    private GetTvShowFromApi getTvShowFromApi = mock(GetTvShowFromApi.class);
    private GetTvShowIdFromApi getTvShowIdFromApi = mock(GetTvShowIdFromApi.class);
    private InsertTvShowToDatabase insertTvShowToDatabase = mock(InsertTvShowToDatabase.class);
    private InsertTvShowToTopList insertTvShowToTopList = mock(InsertTvShowToTopList.class);

    private CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase =
            new CollectImdbTopTvShowsUseCase(getImdbTopTvShows, getTvShowFromApi, getTvShowIdFromApi,
                    insertTvShowToTopList, insertTvShowToDatabase);

    @Test
    public void shouldGetTopTvShowsAndInsertToDataProvider() throws Exception {
        List<String> imdbIds = new LinkedList<>();
        imdbIds.add("1");
        Optional<TvMazeId> tvMazeId = Optional.of(new TvMazeId("1"));
        Optional<TvShowRemote> tvShowEntity = Optional.of(new TvShowRemote());
        Optional<TvShowLocal> tvShowLocal = Optional.of(new TvShowLocal());

        when(getImdbTopTvShows.getImdbIds(URL)).thenReturn(imdbIds);
        when(getTvShowIdFromApi.getTvShowIdByImdbId("1")).thenReturn(tvMazeId);
        when(getTvShowFromApi.getTvShowByMazeId(tvMazeId.get())).thenReturn(tvShowEntity);
        when(insertTvShowToDatabase.insertTvShow(tvShowEntity.get())).thenReturn(tvShowLocal);

        collectImdbTopTvShowsUseCase.collect(URL);

        verify(getImdbTopTvShows, times(1)).getImdbIds(URL);
        verify(getTvShowIdFromApi, times(1)).getTvShowIdByImdbId(imdbIds.get(0));
        verify(getTvShowFromApi, times(1)).getTvShowByMazeId(tvMazeId.get());
        verify(insertTvShowToDatabase, times(1)).insertTvShow(tvShowEntity.get());
        verify(insertTvShowToTopList, times(1)).insert(new TvShowTopListLocal(1, null));
    }

}