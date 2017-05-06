package pl.hypeapp.episodie.entrypoints.rest.tvshow.getmostpopular;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularTvShowsUseCase;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class GetMostPopularTvShowsEndpointTest {

    private GetMostPopularTvShowsUseCase getMostPopularTvShowsUseCase = mock(GetMostPopularTvShowsUseCase.class);

    private GetMostPopularTvShowsEndpoint getMostPopularTvShowsEndpoint = new GetMostPopularTvShowsEndpoint(getMostPopularTvShowsUseCase);

    @Test
    public void shouldGetTvShowTopList() throws Exception {
        Pageable pageable = new PageRequest(0, 3);
        List<TvShowLocal> tvShows = new ArrayList<>();
        tvShows.add(new TvShowLocal());
        tvShows.add(new TvShowLocal());
        tvShows.add(new TvShowLocal());
        Page<TvShowLocal> tvShowLocalPage = new PageImpl<>(tvShows, pageable, pageable.getPageSize());

        when(getMostPopularTvShowsUseCase.getMostPopular(pageable)).thenReturn(tvShowLocalPage);

        getMostPopularTvShowsEndpoint.getMostPopular(pageable);

        verify(getMostPopularTvShowsUseCase, times(1)).getMostPopular(pageable);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowsException() throws Exception {
        Pageable pageable = new PageRequest(0, 3);
        ResourceNotFoundException exception = new ResourceNotFoundException();

        when(getMostPopularTvShowsUseCase.getMostPopular(pageable)).thenThrow(exception);

        getMostPopularTvShowsUseCase.getMostPopular(pageable);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> getMostPopularTvShowsUseCase.getMostPopular(pageable));
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> getMostPopularTvShowsEndpoint.getMostPopular(pageable));
    }

}