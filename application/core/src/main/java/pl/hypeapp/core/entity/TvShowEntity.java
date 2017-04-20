package pl.hypeapp.core.entity;

import java.util.List;

public interface TvShowEntity<SeasonType, EpisodeType> {

    String getTvShowApiId();

    String getImdbId();

    String getName();

    String getStatus();

    Integer getRuntime();

    Integer getFullRuntime();

    String getPremiered();

    String getSummary();

    String getImageMedium();

    String getImageOriginal();

    Integer getUpdated();

    List<SeasonType> getSeasons();

    List<EpisodeType> getEpisodes();

}
