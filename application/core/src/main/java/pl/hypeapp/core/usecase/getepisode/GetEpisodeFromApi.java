package pl.hypeapp.core.usecase.getepisode;

import pl.hypeapp.core.entity.Episode;

public interface GetEpisodeFromApi {

    Episode getEpisode(String tvMazeId);
}
