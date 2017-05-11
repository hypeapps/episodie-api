package pl.hypeapp.episodie.core.usecase.tvshow;

import pl.hypeapp.episodie.core.entity.TvShowEntity;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

import java.util.Optional;

@FunctionalInterface
public interface InsertTvShowToDatabase {

    Optional<TvShowLocal> insertTvShow(TvShowEntity episodeEntity);

}
