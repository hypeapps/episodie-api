package pl.hypeapp.core.usecase.tvshow;

import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.entity.database.TvShowsUpdatesLocal;

public interface GetTvShowFromDatabase {

    TvShowLocal getTvShow(String tvMazeId);

    boolean doesTvShowExist(String tvMazeId);

    TvShowsUpdatesLocal getUpdates();

}
