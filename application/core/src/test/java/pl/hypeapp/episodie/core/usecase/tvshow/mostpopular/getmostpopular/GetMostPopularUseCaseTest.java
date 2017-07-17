package pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetMostPopularUseCaseTest {

    private GetMostPopularTvShows getMostPopularTvShows = mock(GetMostPopularTvShows.class);

    private GetMostPopularUseCase getMostPopularUseCase = new GetMostPopularUseCase(getMostPopularTvShows);

    @Test
    public void shouldThrowsExceptionWhenPageIsNotFound() throws Exception {
        Pageable pageable = new PageRequest(40, 3);
        List<TvShowMostPopularLocal> tvShowMostPopularLocal = new ArrayList<>();
        tvShowMostPopularLocal.add(new TvShowMostPopularLocal(1, "11"));
        tvShowMostPopularLocal.add(new TvShowMostPopularLocal(2, "12"));
        tvShowMostPopularLocal.add(new TvShowMostPopularLocal(3, "13"));
        Page<TvShowMostPopularLocal> tvShowMostPopularPage = new PageImpl<>(tvShowMostPopularLocal, pageable, 114);

        ResourceNotFoundException exception = new ResourceNotFoundException();

        when(getMostPopularTvShows.getMostPopular(pageable)).thenReturn(tvShowMostPopularPage);
        when(getMostPopularUseCase.getMostPopular(pageable)).thenThrow(exception);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> getMostPopularUseCase.getMostPopular(pageable));
    }

}