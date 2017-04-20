package pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows;

import pl.hypeapp.core.entity.api.tvmaze.TvMazeId;

import java.util.Optional;

public interface GetTvShowIdFromApi {

    Optional<TvMazeId> getTvShowIdByImdbId(String imdbId);

}
