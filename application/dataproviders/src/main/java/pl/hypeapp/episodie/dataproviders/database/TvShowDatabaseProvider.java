package pl.hypeapp.episodie.dataproviders.database;

import pl.hypeapp.core.entity.tvmaze.Episode;
import pl.hypeapp.core.usecase.tvshow.gettvshow.DoesTvShowExist;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowFromDatabase;
import pl.hypeapp.core.usecase.tvshow.gettvshow.InsertTvShowToDatabase;

public class TvShowDatabaseProvider implements DoesTvShowExist, GetTvShowFromDatabase, InsertTvShowToDatabase {

    private final TvShowRepository tvShowRepository;

    public TvShowDatabaseProvider(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public boolean doesTvShowExist(String tvMazeId) {
        return true;
    }

    @Override
    public Episode getTvShow(String tvMazeId) {
        Episode episode = new Episode();
        return episode;
    }

    @Override
    public void insertTvShow(Episode episode) {

    }
}
