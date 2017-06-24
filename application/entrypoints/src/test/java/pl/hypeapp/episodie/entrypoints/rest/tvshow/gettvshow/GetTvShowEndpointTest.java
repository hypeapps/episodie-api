package pl.hypeapp.episodie.entrypoints.rest.tvshow.gettvshow;

import org.junit.Test;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.SeasonLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.TvShowNotFoundException;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowExtendedDto;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTvShowEndpointTest {

    private GetTvShowUseCase getTvShowUseCase = mock(GetTvShowUseCase.class);

    private GetTvShowEndpoint getTvShowEndpoint = new GetTvShowEndpoint(getTvShowUseCase);

    @Test
    public void shouldReturnTvShowDto() throws Exception {
        String tvMazeId = "82";
        List<EpisodeLocal> episodeLocals = new ArrayList<>();
        episodeLocals.add(new EpisodeLocal());
        List<SeasonLocal> seasonLocals = new ArrayList<>();
        seasonLocals.add(new SeasonLocal());

        TvShowLocal tvShowLocal = TvShowLocal.builder()
                .tvShowApiId(tvMazeId)
                .episodes(episodeLocals)
                .seasons(seasonLocals)
                .build();

        when(getTvShowUseCase.getTvShow(tvMazeId)).thenReturn(tvShowLocal);

        TvShowExtendedDto tvShowExtendedDto = getTvShowEndpoint.getTvShow(tvMazeId);

        assertSame(tvShowExtendedDto.getTvShowApiId(), tvShowLocal.getTvShowApiId());
    }

    @Test(expected = TvShowNotFoundException.class)
    public void shouldThrowsException() throws Exception {
        String tvMazeId = "83";
        TvShowNotFoundException tvShowNotFoundException = new TvShowNotFoundException();

        when(getTvShowUseCase.getTvShow(tvMazeId)).thenThrow(tvShowNotFoundException);

        getTvShowUseCase.getTvShow(tvMazeId);

        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> getTvShowEndpoint.getTvShow(tvMazeId));
    }

}
