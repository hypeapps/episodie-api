package pl.hypeapp.episodie.core.usecase.tvshow.gettvshow;

public class GetTvShowUseCaseTest {

//    private GetTvShowFromDatabase getTvShowFromDatabase = mock(GetTvShowFromDatabase.class);
//
//    private GetTvShowUseCase getTvShowUseCase = new GetTvShowUseCase(getTvShowFromDatabase, getTvShowFromApi, insertTvShowToDatabase);
//
//    @Test
//    public void shouldGetTvShowFromDatabase() {
//        String tvMazeId = "82";
//        TvShowLocal tvShowLocal = new TvShowLocal();
//
//        when(getTvShowFromDatabase.doesTvShowExist(tvMazeId)).thenReturn(true);
//        when(getTvShowUseCase.getTvShowFromLocal(tvMazeId)).thenReturn(tvShowLocal);
//
//        getTvShowUseCase.getTvShowFromLocal(tvMazeId);
//
//        verify(getTvShowFromDatabase, times(1)).getTvShow(tvMazeId);
//    }
//
//    @Test(expected = TvShowNotFoundException.class)
//    public void shouldThrowExceptionWhenEpisodeIsNotFound() {
//        String tvMazeId = "83";
//        TvShowNotFoundException tvShowNotFoundException = new TvShowNotFoundException();
//
//        when(getTvShowFromDatabase.doesTvShowExist(tvMazeId)).thenReturn(false);
//        when(getTvShowUseCase.getTvShowFromLocal(tvMazeId)).thenThrow(tvShowNotFoundException);
//
//        getTvShowUseCase.getTvShowFromLocal(tvMazeId);
//
//        assertThatExceptionOfType(TvShowNotFoundException.class).isThrownBy(() -> getTvShowUseCase.getTvShowFromLocal(tvMazeId));
//    }

}