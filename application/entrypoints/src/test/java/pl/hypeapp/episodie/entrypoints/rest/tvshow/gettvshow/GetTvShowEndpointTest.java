package pl.hypeapp.episodie.entrypoints.rest.tvshow.gettvshow;

import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.GetTvShowUseCase;

import static org.mockito.Mockito.mock;

public class GetTvShowEndpointTest {

    private GetTvShowUseCase getTvShowUseCase = mock(GetTvShowUseCase.class);

    private GetTvShowEndpoint getTvShowEndpoint = new GetTvShowEndpoint(getTvShowUseCase);

//    @Test
//    public void shouldReturnTvShowDto() {
//        String tvMazeId = "82";
//        List<EpisodeLocal> episodeLocals = new ArrayList<>();
//        episodeLocals.add(new EpisodeLocal());
//        List<SeasonLocal> seasonLocals = new ArrayList<>();
//        seasonLocals.add(new SeasonLocal());
//
//        TvShowLocal tvShowLocal = TvShowLocal.builder()
//                .tvShowApiId(tvMazeId)
//                .episodes(episodeLocals)
//                .seasons(seasonLocals)
//                .build();
//
//        when(getTvShowUseCase.getTvShowFromLocal(tvMazeId)).thenReturn(tvShowLocal);
//
//        TvShowExtendedDto tvShowExtendedDto = getTvShowEndpoint.getTvShowExtended(tvMazeId);
//
//        assertSame(tvShowExtendedDto.getTvShowApiId(), tvShowLocal.getTvShowApiId());
//    }

//    @Test(expected = TvShowNotFoundException.class)
//    public void shouldThrowsException() {
//        String tvMazeId = "83";
//        TvShowNotFoundException tvShowNotFoundException = new TvShowNotFoundException();
//
//        when(getTvShowUseCase.getTvShowFromLocal(tvMazeId)).thenThrow(tvShowNotFoundException);
//
//        getTvShowUseCase.getTvShowFromLocal(tvMazeId);
//
//        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> getTvShowEndpoint.getTvShowExtended(tvMazeId));
//    }

}
