package pl.hypeapp.episodie.core.usecase.tvshow.gettvshow;

import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;

import java.util.Optional;

public class GetTvShowUseCase {

    private final GetTvShowFromDatabase getTvShowFromDatabase;

    private final GetTvShowFromApi getTvShowFromApi;

    private final InsertTvShowToDatabase insertTvShowToDatabase;

    public GetTvShowUseCase(GetTvShowFromDatabase getTvShowFromDatabase, GetTvShowFromApi getTvShowFromApi,
                            InsertTvShowToDatabase insertTvShowToDatabase) {
        this.getTvShowFromDatabase = getTvShowFromDatabase;
        this.getTvShowFromApi = getTvShowFromApi;
        this.insertTvShowToDatabase = insertTvShowToDatabase;
    }

    public TvShowLocal getTvShowFromLocal(String tvMazeId) {
        boolean tvShowExist = getTvShowFromDatabase.doesTvShowExist(tvMazeId);
        if (!tvShowExist) {
            throw new TvShowNotFoundException();
        }
        return getTvShowFromDatabase.getTvShow(tvMazeId);
    }

    public TvShowLocal getTvShow(String tvMazeId) throws ResourceNotFoundException {
        TvShowLocal tvShowLocal;
        try {
            tvShowLocal = getTvShowFromLocal(tvMazeId);
        } catch (TvShowNotFoundException ex) {
            try {
                tvShowLocal = insertTvShowToDataProvider(getTvShowFromApi(tvMazeId));
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException();
            }
        }
        return tvShowLocal;
    }

    private TvShowLocal insertTvShowToDataProvider(TvShowRemote tvShowRemote) {
        Optional<TvShowLocal> tvShowInserted = insertTvShowToDatabase.insertTvShow(tvShowRemote);
        if (!tvShowInserted.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return tvShowInserted.get();
    }

    private TvShowRemote getTvShowFromApi(String id) {
        Optional<TvShowRemote> tvShowRemote = getTvShowFromApi.getTvShowByMazeId(new TvMazeId(id));
        if (!tvShowRemote.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return tvShowRemote.get();
    }

}
