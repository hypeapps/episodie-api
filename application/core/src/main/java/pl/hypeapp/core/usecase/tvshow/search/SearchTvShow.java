package pl.hypeapp.core.usecase.tvshow.search;

import pl.hypeapp.core.entity.database.TvShowLocal;

import java.util.List;

public interface SearchTvShow {

    List<TvShowLocal> search(String query);

}
