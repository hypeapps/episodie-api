package pl.hypeapp.core.usecase.tvshow.gettvshow;

import org.junit.Test;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromDatabase;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class GetTvShowUseCaseTest {

    private GetTvShowFromDatabase getTvShowFromDatabase = mock(GetTvShowFromDatabase.class);

    private GetTvShowUseCase getTvShowUseCase = new GetTvShowUseCase(getTvShowFromDatabase);

    @Test
    public void shouldGetTvShowFromDatabase() throws Exception {
        String tvMazeId = "82";
        TvShowLocal tvShowLocal = new TvShowLocal();

        when(getTvShowFromDatabase.doesTvShowExist(tvMazeId)).thenReturn(true);
        when(getTvShowUseCase.getTvShow(tvMazeId)).thenReturn(tvShowLocal);

        getTvShowUseCase.getTvShow(tvMazeId);

        verify(getTvShowFromDatabase, times(1)).getTvShow(tvMazeId);
    }

    @Test(expected = TvShowNotFoundException.class)
    public void shouldThrowExceptionWhenEpisodeIsNotFound() throws Exception {
        String tvMazeId = "83";
        TvShowNotFoundException tvShowNotFoundException = new TvShowNotFoundException();

        when(getTvShowFromDatabase.doesTvShowExist(tvMazeId)).thenReturn(false);
        when(getTvShowUseCase.getTvShow(tvMazeId)).thenThrow(tvShowNotFoundException);

        getTvShowUseCase.getTvShow(tvMazeId);

        assertThatExceptionOfType(TvShowNotFoundException.class).isThrownBy(() -> getTvShowUseCase.getTvShow(tvMazeId));
    }

}