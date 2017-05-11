package pl.hypeapp.episodie.core.usecase.tvshow.update;

import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowsUpdatesRemote;

import java.util.Optional;

@FunctionalInterface
public interface GetTvShowsUpdates {

    Optional<TvShowsUpdatesRemote> getUpdates();

}
