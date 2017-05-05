package pl.hypeapp.core.usecase.tvshow.gettvshow;

import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromDatabase;

public class GetTvShowUseCase {

    private final GetTvShowFromDatabase getTvShowFromDatabase;

    public GetTvShowUseCase(GetTvShowFromDatabase getTvShowFromDatabase) {
        this.getTvShowFromDatabase = getTvShowFromDatabase;
    }

    public TvShowLocal getTvShow(String tvMazeId) {
        boolean tvShowExist = getTvShowFromDatabase.doesTvShowExist(tvMazeId);
        if (!tvShowExist) {
            throw new TvShowNotFoundException();
        }
        return getTvShowFromDatabase.getTvShow(tvMazeId);
    }

}
