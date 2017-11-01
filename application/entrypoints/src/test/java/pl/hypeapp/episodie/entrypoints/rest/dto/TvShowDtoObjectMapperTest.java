package pl.hypeapp.episodie.entrypoints.rest.dto;

import org.junit.Test;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.SeasonLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.entrypoints.rest.dto.mapper.TvShowDtoObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class TvShowDtoObjectMapperTest {

    private TvShowDtoObjectMapper tvShowDtoObjectMapper = new TvShowDtoObjectMapper();

    @Test
    public void shouldMapLocalToDto() throws Exception {
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

        TvShowExtendedDto tvShowExtendedDto = tvShowDtoObjectMapper.tvShowLocalToDtoExtendedAfterPremiereDate.apply(tvShowLocal);

        assertSame(tvShowExtendedDto.getTvShowApiId(), tvShowLocal.getTvShowApiId());
    }

}
