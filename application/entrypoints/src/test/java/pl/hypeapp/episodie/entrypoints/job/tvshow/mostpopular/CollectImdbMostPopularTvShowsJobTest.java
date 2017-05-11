package pl.hypeapp.episodie.entrypoints.job.tvshow.mostpopular;

import org.junit.Test;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsException;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class CollectImdbMostPopularTvShowsJobTest {

    private static final String URL_MOST_POPULAR_TV_SHOWS = "http://www.imdb.com/chart/tvmeter";

    private CollectImdbMostPopularTvShowsUseCase collectImdbMostPopularTvShowsUseCase = mock(CollectImdbMostPopularTvShowsUseCase.class);

    private CollectImdbMostPopularTvShowsJobResult collectImdbMostPopularTvShowsJobResult = mock(CollectImdbMostPopularTvShowsJobResult.class);

    private CollectImdbMostPopularTvShowsJob collectImdbMostPopularTvShowsJob = new CollectImdbMostPopularTvShowsJob(collectImdbMostPopularTvShowsUseCase,
            collectImdbMostPopularTvShowsJobResult);

    @Test
    public void shouldRunJobUseCaseAndAuditSuccess() throws Exception {
        collectImdbMostPopularTvShowsJob.run();

        verify(collectImdbMostPopularTvShowsUseCase, times(1)).collect(URL_MOST_POPULAR_TV_SHOWS);
        verify(collectImdbMostPopularTvShowsJobResult, times(1)).recordJobSuccessfulResult();
    }

    @Test
    public void shouldThrowExceptionAndAuditUnSuccessfulJob() throws Exception {
        CollectImdbMostPopularTvShowsException collectImdbMostPopularTvShowsException = new CollectImdbMostPopularTvShowsException();
        doThrow(collectImdbMostPopularTvShowsException).when(collectImdbMostPopularTvShowsUseCase).collect(URL_MOST_POPULAR_TV_SHOWS);

        collectImdbMostPopularTvShowsJob.run();

        assertThatExceptionOfType(CollectImdbMostPopularTvShowsException.class).isThrownBy(() -> collectImdbMostPopularTvShowsUseCase.collect(URL_MOST_POPULAR_TV_SHOWS));
        verify(collectImdbMostPopularTvShowsJobResult, times(1)).recordJobUnsuccessfulResult(collectImdbMostPopularTvShowsException.getMessage());
    }

    @Test
    public void jobHasAllDetails() throws Exception {
        assertThat(collectImdbMostPopularTvShowsJob.getName()).isNotEmpty();
        assertThat(collectImdbMostPopularTvShowsJob.getInitialDelay()).isNotNull();
        assertThat(collectImdbMostPopularTvShowsJob.getPeriod()).isNotNull();
        assertThat(collectImdbMostPopularTvShowsJob.getTimeUnit()).isNotNull();
    }

}