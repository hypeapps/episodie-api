package pl.hypeapp.episodie.entrypoints.rest.tvshow.getmostpopular;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.SeasonLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularUseCase;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class GetMostPopularTvShowsEndpointTest {

    private GetMostPopularUseCase getMostPopularUseCase = mock(GetMostPopularUseCase.class);

    private GetMostPopularTvShowsEndpoint getMostPopularTvShowsEndpoint = new GetMostPopularTvShowsEndpoint(getMostPopularUseCase);

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

        when(getMostPopularUseCase.getMostPopular(pageable)).thenReturn(tvShowLocalPage);

        getMostPopularTvShowsEndpoint.getMostPopularExtended(pageable);

        verify(getMostPopularUseCase, times(1)).getMostPopular(pageable);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowsException() throws Exception {
        Pageable pageable = new PageRequest(0, 3);
        ResourceNotFoundException exception = new ResourceNotFoundException();

        when(getMostPopularUseCase.getMostPopular(pageable)).thenThrow(exception);

        getMostPopularUseCase.getMostPopular(pageable);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> getMostPopularUseCase.getMostPopular(pageable));
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> getMostPopularTvShowsEndpoint.getMostPopularExtended(pageable));
    }

}