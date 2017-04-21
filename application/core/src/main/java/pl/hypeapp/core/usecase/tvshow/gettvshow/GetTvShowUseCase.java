package pl.hypeapp.core.usecase.tvshow.gettvshow;

import pl.hypeapp.core.entity.api.tvmaze.EpisodeRemote;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;

public class GetTvShowUseCase {
    private final GetTvShowFromApi getTvShowFromApi;
//    private final InsertTvShowToDatabase insertTvShowToDatabase;
//    private final GetTvShowFromDatabase getTvShowFromDatabase;
//    private final DoesTvShowExist doesTvShowExist;

//    public GetTvShowUseCase(DoesTvShowExist doesTvShowExist, GetTvShowFromApi getTvShowFromApi,
//                            GetTvShowFromDatabase getTvShowFromDatabase, InsertTvShowToDatabase insertTvShowToDatabase) {
//        this.doesTvShowExist = doesTvShowExist;
//        this.getTvShowFromDatabase = getTvShowFromDatabase;
//        this.getTvShowFromApi = getTvShowFromApi;
//        this.insertTvShowToDatabase = insertTvShowToDatabase;
//    }

    public GetTvShowUseCase(GetTvShowFromApi getTvShowFromApi) {
//        this.doesTvShowExist = doesTvShowExist;
//        this.getTvShowFromDatabase = getTvShowFromDatabase;
        this.getTvShowFromApi = getTvShowFromApi;
//        this.insertTvShowToDatabase = insertTvShowToDatabase;
    }

    public EpisodeRemote getEpisode(String tvmazeId) {
        EpisodeRemote episodeRemote;
//        if(doesTvShowExist.doesTvShowExist(tvmazeId)) {
//            episodeRemote = getTvShowFromDatabase.getTvShow(tvmazeId);
//        } else {
        episodeRemote = null;
//            tvshow = getTvShowFromApi.getTvShowByMazeId(tvmazeId);
//        }
        if (episodeRemote == null) {
            throw new EpisodeNotFoundException();
        }
        return episodeRemote;
    }
}
