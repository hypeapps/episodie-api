package pl.hypeapp.episodie.dataproviders.database.toplist;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowTopListLocal;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TvShowTopListDatabaseProviderTest {

    private TvShowTopListRepository tvShowTopListRepository = mock(TvShowTopListRepository.class);

    private TvShowTopListDatabaseProvider tvShowTopListDatabaseProvider = new TvShowTopListDatabaseProvider(tvShowTopListRepository);

    @Test
    public void shouldInsertTvShowToTopList() throws Exception {
        TvShowTopListLocal tvShowTopListLocal = new TvShowTopListLocal();

        when(tvShowTopListRepository.save(tvShowTopListLocal)).thenReturn(tvShowTopListLocal);

        tvShowTopListDatabaseProvider.insert(tvShowTopListLocal);

        verify(tvShowTopListRepository, times(1)).save(tvShowTopListLocal);
    }

    @Test
    public void shouldReturnsTopList() throws Exception {
        Pageable pageable = new PageRequest(0, 3);

        tvShowTopListDatabaseProvider.getTopList(pageable);

        verify(tvShowTopListRepository, times(1)).findAll(pageable);
    }

    @Test
    public void shouldReturnsTvShowsTopList() throws Exception {
        List<String> topListIds = new ArrayList<>();
        topListIds.add("12");
        topListIds.add("13");

        List<TvShowLocal> tvShows = new ArrayList<>();
        tvShows.add(new TvShowLocal());
        tvShows.add(new TvShowLocal());

        when(tvShowTopListRepository.getTopListTvShows(topListIds)).thenReturn(tvShows);

        tvShowTopListDatabaseProvider.getTopListTvShows(topListIds);

        verify(tvShowTopListRepository, times(1)).getTopListTvShows(topListIds);
    }

}