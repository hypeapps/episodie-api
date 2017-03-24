package pl.hypeapp.core.usecase.getepisode;

import pl.hypeapp.core.entity.Episode;

public interface GetEpisodeFromDatabase {

    Episode getEpisode(String tvMazeId);
}
