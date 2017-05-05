package pl.hypeapp.core.usecase.tvshow.update;

import org.junit.Ignore;
import org.junit.Test;
import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.entity.api.tvmaze.TvShowsUpdatesRemote;
import pl.hypeapp.core.entity.database.TvShowsUpdatesLocal;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromDatabase;
import pl.hypeapp.core.usecase.tvshow.InsertTvShowToDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateTvShowsUseCaseTest {

    private GetTvShowFromApi getTvShowFromApi = mock(GetTvShowFromApi.class);

    private GetTvShowFromDatabase getTvShowFromDatabase = mock(GetTvShowFromDatabase.class);

    private GetTvShowsUpdates getTvShowsUpdates = mock(GetTvShowsUpdates.class);

    private InsertTvShowToDatabase insertTvShowToDatabase = mock(InsertTvShowToDatabase.class);

    private UpdateTvShowsUseCase updateTvShowsUseCase = new UpdateTvShowsUseCase(getTvShowFromApi, getTvShowFromDatabase,
            getTvShowsUpdates, insertTvShowToDatabase);

    @Test
    @Ignore
    public void shouldGetTvShowsToUpdateAndInsertToDatabase() throws Exception {
        Map<String, Integer> localUpdates = new HashMap<>();
        localUpdates.put("1", 123213);
        localUpdates.put("2", 123123);
        Map<String, Integer> remoteUpdates = new HashMap<>();
        remoteUpdates.put("1", 1111111);
        remoteUpdates.put("2", 123123);

        Optional<TvShowsUpdatesRemote> tvShowsUpdatesRemote = Optional.of(new TvShowsUpdatesRemote(remoteUpdates));
        TvShowsUpdatesLocal tvShowsUpdatesLocal = new TvShowsUpdatesLocal(localUpdates);

        Optional<TvMazeId> tvMazeId = Optional.of(new TvMazeId(remoteUpdates.get("1").toString()));
        Optional<TvShowRemote> tvShowEntity = Optional.of(new TvShowRemote());

        when(getTvShowsUpdates.getUpdates()).thenReturn(tvShowsUpdatesRemote);
        when(getTvShowFromDatabase.getUpdates()).thenReturn(tvShowsUpdatesLocal);
        when(getTvShowFromApi.getTvShowByMazeId(tvMazeId.get())).thenReturn(tvShowEntity);

        //TODO: end test
    }

}