package pl.hypeapp.core.usecase.tvshow;

import pl.hypeapp.core.entity.TvShowEntity;
import pl.hypeapp.core.entity.database.TvShowLocal;

import java.util.Optional;

@FunctionalInterface
public interface InsertTvShowToDatabase {

    Optional<TvShowLocal> insertTvShow(TvShowEntity episodeEntity);

}
