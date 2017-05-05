package pl.hypeapp.episodie.entrypoints.job.tvshow.top;

import org.junit.Test;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsException;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class CollectImdbTopTvShowsJobTest {

    private static final String IMDB_TOP_TV_SHOWS_URL = "http://www.imdb.com/chart/toptv";

    private CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase = mock(CollectImdbTopTvShowsUseCase.class);

    private CollectImdbTopTvShowsJobResult collectImdbTopTvShowsJobResult = mock(CollectImdbTopTvShowsJobResult.class);

    private CollectImdbTopTvShowsJob collectImdbTopTvShowsJob = new CollectImdbTopTvShowsJob(collectImdbTopTvShowsUseCase,
            collectImdbTopTvShowsJobResult);

    @Test
    public void shouldRunJobUseCaseAndAuditSuccess() throws Exception {
        collectImdbTopTvShowsJob.run();

        verify(collectImdbTopTvShowsUseCase, times(1)).collect(IMDB_TOP_TV_SHOWS_URL);
        verify(collectImdbTopTvShowsJobResult, times(1)).recordJobSuccessfulResult();
    }

    @Test
    public void shouldThrowExceptionAndAuditUnSuccessfulJob() throws Exception {
        CollectImdbTopTvShowsException collectImdbTopTvShowsException = new CollectImdbTopTvShowsException();
        doThrow(collectImdbTopTvShowsException).when(collectImdbTopTvShowsUseCase).collect(IMDB_TOP_TV_SHOWS_URL);

        collectImdbTopTvShowsJob.run();

        assertThatExceptionOfType(CollectImdbTopTvShowsException.class).isThrownBy(() -> collectImdbTopTvShowsUseCase.collect(IMDB_TOP_TV_SHOWS_URL));
        verify(collectImdbTopTvShowsJobResult, times(1)).recordJobUnsuccessfulResult(collectImdbTopTvShowsException.getMessage());
    }

    @Test
    public void jobHasAllDetails() throws Exception {
        assertThat(collectImdbTopTvShowsJob.getName()).isNotEmpty();
        assertThat(collectImdbTopTvShowsJob.getInitialDelay()).isNotNull();
        assertThat(collectImdbTopTvShowsJob.getPeriod()).isNotNull();
        assertThat(collectImdbTopTvShowsJob.getTimeUnit()).isNotNull();
    }

}
