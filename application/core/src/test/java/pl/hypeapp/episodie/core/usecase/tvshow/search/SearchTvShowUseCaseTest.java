package pl.hypeapp.episodie.core.usecase.tvshow.search;

import org.junit.Test;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SearchTvShowUseCaseTest {

    private SearchTvShow searchTvShow = mock(SearchTvShow.class);

    private SearchTvShowInApi searchTvShowInApi = mock(SearchTvShowInApi.class);

    private GetTvShowFromApi getTvShowFromApi = mock(GetTvShowFromApi.class);

    private InsertTvShowToDatabase insertTvShowToDatabase = mock(InsertTvShowToDatabase.class);

    private SearchTvShowUseCase searchTvShowUseCase = new SearchTvShowUseCase(searchTvShow, searchTvShowInApi, getTvShowFromApi, insertTvShowToDatabase);

    @Test
    public void shouldSearchForTvShowInDatabase() throws Exception {
        String query = "some query";
        List<TvShowLocal> searchResults = new ArrayList<>();
        List<TvShowLocal> searchDatabaseResults = new ArrayList<>();
        searchDatabaseResults.add(new TvShowLocal());
        Optional<TvMazeId> tvMazeId = Optional.empty();

        when(searchTvShow.search(query)).thenReturn(searchDatabaseResults);
        when(searchTvShowInApi.search(query)).thenReturn(tvMazeId);

        searchResults.addAll(searchTvShowUseCase.search(query));

        verify(searchTvShow, times(1)).search(query);
        verify(searchTvShowInApi, times(1)).search(query);
        assertEquals(1, searchResults.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowExceptionWhenQueryIsEmpty() throws Exception {
        String query = "";

        searchTvShowUseCase.search(query);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> searchTvShowUseCase.search(query));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowExceptionWhenSearchResultsIsEmpty() throws Exception {
        String query = "some query";
        List<TvShowLocal> searchResults = new ArrayList<>();
        List<TvShowLocal> searchDatabaseResults = new ArrayList<>();
        Optional<TvMazeId> tvMazeId = Optional.empty();

        when(searchTvShow.search(query)).thenReturn(searchDatabaseResults);
        when(searchTvShowInApi.search(query)).thenReturn(tvMazeId);

        searchResults.addAll(searchTvShowUseCase.search(query));

        assertEquals(0, searchResults.size());
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> searchTvShowUseCase.search(query));
    }

}