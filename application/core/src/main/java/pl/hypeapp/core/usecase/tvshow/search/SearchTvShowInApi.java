package pl.hypeapp.core.usecase.tvshow.search;

import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;

import java.util.Optional;

@FunctionalInterface
public interface SearchTvShowInApi {

    Optional<TvMazeId> search(String query);

}
