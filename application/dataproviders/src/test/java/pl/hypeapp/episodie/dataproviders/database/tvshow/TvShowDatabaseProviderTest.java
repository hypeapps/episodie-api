package pl.hypeapp.episodie.dataproviders.database.tvshow;

import org.junit.Ignore;
import org.junit.Test;
import pl.hypeapp.core.entity.TvShowEntity;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.core.entity.database.TvShowDatabaseAdapter;
import pl.hypeapp.core.entity.database.TvShowLocal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TvShowDatabaseProviderTest {
    private TvShowRepository tvShowRepository = mock(TvShowRepository.class);

    private TvShowDatabaseProvider tvShowDatabaseProvider = new TvShowDatabaseProvider(tvShowRepository);

    @Test
    @Ignore
    public void shouldCallSaveOnRepository() {
        TvShowEntity tvShowEntity = new TvShowRemote();
        TvShowDatabaseAdapter tvShowDatabaseAdapter = new TvShowDatabaseAdapter((TvShowRemote) tvShowEntity);
        TvShowLocal tvShowLocal = any(TvShowLocal.class);

        when(tvShowDatabaseAdapter.apply()).thenReturn(tvShowLocal);

        when(tvShowRepository.save(tvShowLocal)).thenReturn(tvShowLocal);

        tvShowDatabaseProvider.insertTvShow(tvShowEntity);

        verify(tvShowRepository, times(1)).save(tvShowLocal);
    }

}