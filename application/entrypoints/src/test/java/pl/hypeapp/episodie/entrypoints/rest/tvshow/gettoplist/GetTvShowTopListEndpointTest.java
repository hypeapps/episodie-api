package pl.hypeapp.episodie.entrypoints.rest.tvshow.gettoplist;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.SeasonLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.gettoplist.GetTopListUseCase;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class GetTvShowTopListEndpointTest {

    private GetTopListUseCase getTopListUseCase = mock(GetTopListUseCase.class);

    private GetTvShowTopListEndpoint getTvShowTopListEndpoint = new GetTvShowTopListEndpoint(getTopListUseCase);

    @Test
    public void shouldGetTvShowTopList() throws Exception {
        Pageable pageable = new PageRequest(0, 3);
        List<TvShowLocal> tvShows = new ArrayList<>();
        tvShows.add(TvShowLocal.builder()
                .seasons(Collections.singletonList(new SeasonLocal()))
                .episodes(Collections.singletonList(new EpisodeLocal()))
                .build());
        tvShows.add(TvShowLocal.builder()
                .seasons(Collections.singletonList(new SeasonLocal()))
                .episodes(Collections.singletonList(new EpisodeLocal()))
                .build());
        tvShows.add(TvShowLocal.builder()
                .seasons(Collections.singletonList(new SeasonLocal()))
                .episodes(Collections.singletonList(new EpisodeLocal()))
                .build());

        Page<TvShowLocal> tvShowLocalPage = new PageImpl<>(tvShows, pageable, pageable.getPageSize());

        when(getTopListUseCase.getTopList(pageable)).thenReturn(tvShowLocalPage);

        getTvShowTopListEndpoint.getTopList(pageable);

        verify(getTopListUseCase, times(1)).getTopList(pageable);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowsException() throws Exception {
        Pageable pageable = new PageRequest(0, 3);
        ResourceNotFoundException exception = new ResourceNotFoundException();

        when(getTopListUseCase.getTopList(pageable)).thenThrow(exception);

        getTopListUseCase.getTopList(pageable);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> getTopListUseCase.getTopList(pageable));
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> getTvShowTopListEndpoint.getTopList(pageable));
    }

}
