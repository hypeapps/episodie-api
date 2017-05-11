package pl.hypeapp.episodie.core.usecase.tvshow;

import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;

import java.util.Optional;

@FunctionalInterface
public interface GetTvShowFromApi {

    Optional<TvShowRemote> getTvShowByMazeId(TvMazeId tvMazeId);

}
