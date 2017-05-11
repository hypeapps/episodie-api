package pl.hypeapp.episodie.core.usecase.tvshow;

import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowsUpdatesLocal;

public interface GetTvShowFromDatabase {

    TvShowLocal getTvShow(String tvMazeId);

    boolean doesTvShowExist(String tvMazeId);

    TvShowsUpdatesLocal getUpdates();

}
