package pl.hypeapp.core.usecase.tvshow.update;

import pl.hypeapp.core.entity.api.tvmaze.TvShowsUpdatesRemote;

import java.util.Optional;

@FunctionalInterface
public interface GetTvShowsUpdates {

    Optional<TvShowsUpdatesRemote> getUpdates();

}
