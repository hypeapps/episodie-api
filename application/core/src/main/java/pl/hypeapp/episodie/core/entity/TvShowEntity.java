package pl.hypeapp.episodie.core.entity;

import java.util.List;

public interface TvShowEntity<SeasonType, EpisodeType> {

    String getTvShowApiId();

    String getImdbId();

    String getName();

    String getStatus();

    String getOfficialSite();

    Integer getRuntime();

    Integer getFullRuntime();

    String getPremiered();

    String getNetworkName();

    String getGenre();

    String getSummary();

    String getImageMedium();

    String getImageOriginal();

    Integer getUpdated();

    List<SeasonType> getSeasons();

    List<EpisodeType> getEpisodes();

}
