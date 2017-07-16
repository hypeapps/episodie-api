package pl.hypeapp.episodie.core.usecase.tvshow.toplist.gettoplist;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowTopListLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class GetTopListUseCaseTest {

    private GetTvShowTopList getTvShowTopList = mock(GetTvShowTopList.class);

    private GetTopListUseCase getTopListUseCase = new GetTopListUseCase(getTvShowTopList);

    @Test
    @Ignore
    public void shouldGetTopListFromProvider() throws Exception {
        Pageable pageable = new PageRequest(0, 3);
        List<TvShowLocal> tvShows = new ArrayList<>();
        tvShows.add(new TvShowLocal());
        tvShows.add(new TvShowLocal());
        tvShows.add(new TvShowLocal());
        Page<TvShowLocal> tvShowLocalPage = new PageImpl<>(tvShows, pageable, pageable.getPageSize());

        List<TvShowTopListLocal> tvShowTopList = new ArrayList<>();
        tvShowTopList.add(new TvShowTopListLocal(1, "11"));
        tvShowTopList.add(new TvShowTopListLocal(2, "12"));
        tvShowTopList.add(new TvShowTopListLocal(3, "13"));
        Page<TvShowTopListLocal> tvShowTopListLocalPage = new PageImpl<TvShowTopListLocal>(tvShowTopList, pageable, pageable.getPageSize());

        when(getTvShowTopList.getTopList(pageable)).thenReturn(tvShowTopListLocalPage);

        List<String> topListIds = new ArrayList<>();
        topListIds.add(tvShowTopList.get(0).getTvShowApiId());
        topListIds.add(tvShowTopList.get(1).getTvShowApiId());
        topListIds.add(tvShowTopList.get(2).getTvShowApiId());

        when(getTvShowTopList.getTopListTvShows(topListIds)).thenReturn(tvShows);

        when(getTopListUseCase.getTopList(pageable)).thenReturn(tvShowLocalPage);

        getTopListUseCase.getTopList(pageable);

        verify(getTvShowTopList, times(1)).getTopList(pageable);
        verify(getTvShowTopList, times(1)).getTopListTvShows(topListIds);

    }

    @Test
    public void shouldThrowsExceptionWhenPageIsNotFound() throws Exception {
        Pageable pageable = new PageRequest(40, 3);
        List<TvShowTopListLocal> tvShowTopList = new ArrayList<>();
        tvShowTopList.add(new TvShowTopListLocal(1, "11"));
        tvShowTopList.add(new TvShowTopListLocal(2, "12"));
        tvShowTopList.add(new TvShowTopListLocal(3, "13"));
        Page<TvShowTopListLocal> tvShowTopListLocalPage = new PageImpl<TvShowTopListLocal>(tvShowTopList, pageable, 114);

        ResourceNotFoundException exception = new ResourceNotFoundException();

        when(getTvShowTopList.getTopList(pageable)).thenReturn(tvShowTopListLocalPage);
        when(getTopListUseCase.getTopList(pageable)).thenThrow(exception);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> getTopListUseCase.getTopList(pageable));
    }

}