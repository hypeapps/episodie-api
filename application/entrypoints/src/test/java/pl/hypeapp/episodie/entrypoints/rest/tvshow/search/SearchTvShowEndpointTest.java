package pl.hypeapp.episodie.entrypoints.rest.tvshow.search;

import org.junit.Test;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.SeasonLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.search.SearchTvShowUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowExtendedDto;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class SearchTvShowEndpointTest {

    private SearchTvShowUseCase searchTvShowUseCase = mock(SearchTvShowUseCase.class);

    private SearchTvShowEndpoint searchTvShowEndpoint = new SearchTvShowEndpoint(searchTvShowUseCase);

    @Test
    public void shouldReturnSearchResult() throws Exception {
        String query = "tvshowquery";

        List<EpisodeLocal> episodeLocals = new ArrayList<>();
        episodeLocals.add(new EpisodeLocal());
        List<SeasonLocal> seasonLocals = new ArrayList<>();
        seasonLocals.add(new SeasonLocal());
        List<TvShowLocal> result = new ArrayList<>();

        TvShowLocal tvShowLocal = TvShowLocal.builder()
                .tvShowApiId("82")
                .seasons(seasonLocals)
                .episodes(episodeLocals)
                .build();

        result.add(tvShowLocal);

        when(searchTvShowUseCase.search(query)).thenReturn(result);

        List<TvShowExtendedDto> tvShowExtendedDtos = searchTvShowEndpoint.searchExtended(query);

        assertSame(tvShowExtendedDtos.get(0).getTvShowApiId(), result.get(0).getTvShowApiId());
        verify(searchTvShowUseCase, times(1)).search(query);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowException() throws Exception {
        String query = "";

        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();

        when(searchTvShowUseCase.search(query)).thenThrow(resourceNotFoundException);

        searchTvShowUseCase.search(query);

        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> searchTvShowEndpoint.search(query));
    }

}