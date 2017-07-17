package pl.hypeapp.episodie.entrypoints.job.tvshow.top;

import org.junit.Test;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopListUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class CollectImdbTopListJobTest {

    private static final String IMDB_TOP_TV_SHOWS_URL = "http://www.imdb.com/chart/toptv";

    private static final String JOB_NAME = "CollectImdbTopListJob";

    private CollectImdbTopListUseCase collectImdbTopListUseCase = mock(CollectImdbTopListUseCase.class);

    private CollectImdbTopListJobResult collectImdbTopListJobResult = mock(CollectImdbTopListJobResult.class);

    private CollectImdbTopListJob collectImdbTopListJob = new CollectImdbTopListJob(collectImdbTopListUseCase,
            collectImdbTopListJobResult);

    @Test
    public void shouldRunJobUseCaseAndAuditSuccess() throws Exception {
        collectImdbTopListJob.run();

        verify(collectImdbTopListUseCase, times(1)).collect(IMDB_TOP_TV_SHOWS_URL);
        verify(collectImdbTopListJobResult, times(1)).recordJobSuccessfulResult(JOB_NAME);
    }

    @Test
    public void shouldThrowExceptionAndAuditUnSuccessfulJob() throws Exception {
        CollectImdbTopTvShowsException collectImdbTopTvShowsException = new CollectImdbTopTvShowsException();
        doThrow(collectImdbTopTvShowsException).when(collectImdbTopListUseCase).collect(IMDB_TOP_TV_SHOWS_URL);

        collectImdbTopListJob.run();

        assertThatExceptionOfType(CollectImdbTopTvShowsException.class).isThrownBy(() -> collectImdbTopListUseCase.collect(IMDB_TOP_TV_SHOWS_URL));
        verify(collectImdbTopListJobResult, times(1)).recordJobUnsuccessfulResult(JOB_NAME, collectImdbTopTvShowsException.getMessage());
    }

    @Test
    public void jobHasAllDetails() throws Exception {
        assertThat(collectImdbTopListJob.getName()).isNotEmpty();
        assertThat(collectImdbTopListJob.getInitialDelay()).isNotNull();
        assertThat(collectImdbTopListJob.getPeriod()).isNotNull();
        assertThat(collectImdbTopListJob.getTimeUnit()).isNotNull();
    }

}
