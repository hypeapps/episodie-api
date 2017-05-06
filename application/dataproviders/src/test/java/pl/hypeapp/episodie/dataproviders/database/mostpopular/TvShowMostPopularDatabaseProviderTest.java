package pl.hypeapp.episodie.dataproviders.database.mostpopular;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.entity.database.TvShowMostPopularLocal;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TvShowMostPopularDatabaseProviderTest {

    private TvShowMostPopularRepository tvShowMostPopularRepository = mock(TvShowMostPopularRepository.class);

    private TvShowMostPopularDatabaseProvider tvShowMostPopularDatabaseProvider = new TvShowMostPopularDatabaseProvider(tvShowMostPopularRepository);

    @Test
    public void shouldInsertTvShowToTopList() throws Exception {
        TvShowMostPopularLocal tvShowMostPopularLocal = new TvShowMostPopularLocal(1, "12");

        when(tvShowMostPopularRepository.save(tvShowMostPopularLocal)).thenReturn(tvShowMostPopularLocal);

        tvShowMostPopularDatabaseProvider.insert(tvShowMostPopularLocal);

        verify(tvShowMostPopularRepository, times(1)).save(tvShowMostPopularLocal);
    }

    @Test
    public void shouldReturnsTopList() throws Exception {
        Pageable pageable = new PageRequest(0, 3);

        tvShowMostPopularDatabaseProvider.getMostPopular(pageable);

        verify(tvShowMostPopularRepository, times(1)).findAll(pageable);
    }

    @Test
    public void shouldReturnsTvShowsTopList() throws Exception {
        List<String> topListIds = new ArrayList<>();
        topListIds.add("12");
        topListIds.add("13");

        List<TvShowLocal> tvShows = new ArrayList<>();
        tvShows.add(new TvShowLocal());
        tvShows.add(new TvShowLocal());

        when(tvShowMostPopularRepository.getTopListTvShows(topListIds)).thenReturn(tvShows);

        tvShowMostPopularDatabaseProvider.getMostPopularTvShows(topListIds);

        verify(tvShowMostPopularRepository, times(1)).getTopListTvShows(topListIds);
    }

}