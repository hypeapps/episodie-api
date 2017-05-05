package pl.hypeapp.core.usecase.tvshow;

import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;

import java.util.Optional;

@FunctionalInterface
public interface GetTvShowFromApi {

    Optional<TvShowRemote> getTvShowByMazeId(TvMazeId tvMazeId);

}
