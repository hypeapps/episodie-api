package pl.hypeapp.core.entity;

public interface EpisodeEntity {

    String getEpisodeApiId();

    String getUrl();

    String getName();

    Integer getSeasonNumber();

    Integer getEpisodeNumber();

    String getAirStamp();

    Integer getRuntime();

    String getImageMedium();

    String getImageOriginal();

    String getSummary();

}
