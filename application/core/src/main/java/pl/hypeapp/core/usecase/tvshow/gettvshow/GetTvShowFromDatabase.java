package pl.hypeapp.core.usecase.tvshow.gettvshow;

public interface GetTvShowFromDatabase {

    String getTvShow(String tvMazeId);

    boolean doesTvShowExist(String tvMazeId);
}
