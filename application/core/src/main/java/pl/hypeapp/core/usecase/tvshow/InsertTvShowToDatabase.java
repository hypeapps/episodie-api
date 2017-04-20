package pl.hypeapp.core.usecase.tvshow;

import pl.hypeapp.core.entity.TvShowEntity;
import pl.hypeapp.core.entity.database.TvShowLocal;

import java.util.Optional;

public interface InsertTvShowToDatabase {

    Optional<TvShowLocal> insertTvShow(TvShowEntity episodeEntity);

}
