package pl.hypeapp.episodie.entrypoints.job.tvshow.update;

import org.junit.Test;
import pl.hypeapp.core.usecase.tvshow.update.UpdateTvShowsException;
import pl.hypeapp.core.usecase.tvshow.update.UpdateTvShowsUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class UpdateTvShowsJobTest {
    private UpdateTvShowsUseCase updateTvShowsUseCase = mock(UpdateTvShowsUseCase.class);
    private UpdateTvShowsJobResult updateTvShowsJobResult = mock(UpdateTvShowsJobResult.class);

    private UpdateTvShowsJob updateTvShowsJob = new UpdateTvShowsJob(updateTvShowsUseCase,
            updateTvShowsJobResult);

    @Test
    public void shouldRunJobUseCaseAndAuditSuccess() throws Exception {
        updateTvShowsJob.run();

        verify(updateTvShowsUseCase, times(1)).update();
        verify(updateTvShowsJobResult, times(1)).recordJobSuccessfulResult();
    }

    @Test
    public void shouldThrowExceptionAndAuditUnSuccessfulJob() throws Exception {
        UpdateTvShowsException updateTvShowsException = new UpdateTvShowsException();
        doThrow(updateTvShowsException).when(updateTvShowsUseCase).update();

        updateTvShowsJob.run();

        assertThatExceptionOfType(UpdateTvShowsException.class).isThrownBy(() -> updateTvShowsUseCase.update());
        verify(updateTvShowsJobResult, times(1)).recordJobUnsuccessfulResult(updateTvShowsException.getMessage());
    }

    @Test
    public void jobHasAllDetails() throws Exception {
        assertThat(updateTvShowsJob.getName()).isNotEmpty();
        assertThat(updateTvShowsJob.getInitialDelay()).isNotNull();
        assertThat(updateTvShowsJob.getPeriod()).isNotNull();
        assertThat(updateTvShowsJob.getTimeUnit()).isNotNull();
    }

}