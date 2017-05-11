package pl.hypeapp.episodie.core.usecase.tvshow.search;

import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

import java.util.List;

@FunctionalInterface
public interface SearchTvShow {

    List<TvShowLocal> search(String query);

}
