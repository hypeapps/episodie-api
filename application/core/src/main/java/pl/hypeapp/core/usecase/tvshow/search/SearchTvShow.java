package pl.hypeapp.core.usecase.tvshow.search;

import pl.hypeapp.core.entity.database.TvShowLocal;

import java.util.List;

@FunctionalInterface
public interface SearchTvShow {

    List<TvShowLocal> search(String query);

}
