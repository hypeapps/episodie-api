package pl.hypeapp.episodie.dataproviders.database.toplist;

import org.junit.Test;
import pl.hypeapp.core.entity.database.TvShowTopListLocal;
import pl.hypeapp.episodie.dataproviders.database.tvshow.TvShowRepository;

import static org.mockito.Mockito.*;

public class TvShowTopListDatabaseProviderTest {
    private TvShowRepository tvShowRepository = mock(TvShowRepository.class);
    private TvShowTopListRepository tvShowTopListRepository = mock(TvShowTopListRepository.class);

    private TvShowTopListDatabaseProvider tvShowTopListDatabaseProvider = new TvShowTopListDatabaseProvider(tvShowRepository,
            tvShowTopListRepository);

    @Test
    public void shouldInsertTvShowToTopList() {
        TvShowTopListLocal tvShowTopListLocal = new TvShowTopListLocal();

        when(tvShowTopListRepository.save(tvShowTopListLocal)).thenReturn(tvShowTopListLocal);

        tvShowTopListDatabaseProvider.insert(tvShowTopListLocal);

        verify(tvShowTopListRepository, times(1)).save(tvShowTopListLocal);
    }

}