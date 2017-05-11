package pl.hypeapp.episodie.core.usecase.tvshow;

import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;

import java.util.Optional;

public interface GetTvShowIdFromApi {

    Optional<TvMazeId> getTvShowIdByImdbId(String imdbId);

}
