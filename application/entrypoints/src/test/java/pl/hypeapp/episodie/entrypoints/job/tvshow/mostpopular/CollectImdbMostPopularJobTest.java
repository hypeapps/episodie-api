package pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular;

import org.junit.Test;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsException;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class CollectImdbMostPopularJobTest {

    private static final String URL_MOST_POPULAR_TV_SHOWS = "http://www.imdb.com/chart/tvmeter";

    private static final String JOB_NAME = "CollectImdbMostPopularJob";

    private CollectImdbMostPopularUseCase collectImdbMostPopularUseCase = mock(CollectImdbMostPopularUseCase.class);

    private CollectImdbMostPopularJobResult collectImdbMostPopularJobResult = mock(CollectImdbMostPopularJobResult.class);

    private CollectImdbMostPopularJob collectImdbMostPopularJob = new CollectImdbMostPopularJob(collectImdbMostPopularUseCase,
            collectImdbMostPopularJobResult);

    @Test
    public void shouldRunJobUseCaseAndAuditSuccess() throws Exception {
        collectImdbMostPopularJob.run();

        verify(collectImdbMostPopularUseCase, times(1)).collect(URL_MOST_POPULAR_TV_SHOWS);
        verify(collectImdbMostPopularJobResult, times(1)).recordJobSuccessfulResult(JOB_NAME);
    }

    @Test
    public void shouldThrowExceptionAndAuditUnSuccessfulJob() throws Exception {
        CollectImdbMostPopularTvShowsException collectImdbMostPopularTvShowsException = new CollectImdbMostPopularTvShowsException();
        doThrow(collectImdbMostPopularTvShowsException).when(collectImdbMostPopularUseCase).collect(URL_MOST_POPULAR_TV_SHOWS);

        collectImdbMostPopularJob.run();

        assertThatExceptionOfType(CollectImdbMostPopularTvShowsException.class).isThrownBy(() -> collectImdbMostPopularUseCase.collect(URL_MOST_POPULAR_TV_SHOWS));
        verify(collectImdbMostPopularJobResult, times(1)).recordJobUnsuccessfulResult(JOB_NAME, collectImdbMostPopularTvShowsException.getMessage());
    }

    @Test
    public void jobHasAllDetails() throws Exception {
        assertThat(collectImdbMostPopularJob.getName()).isNotEmpty();
        assertThat(collectImdbMostPopularJob.getInitialDelay()).isNotNull();
        assertThat(collectImdbMostPopularJob.getPeriod()).isNotNull();
        assertThat(collectImdbMostPopularJob.getTimeUnit()).isNotNull();
    }

}