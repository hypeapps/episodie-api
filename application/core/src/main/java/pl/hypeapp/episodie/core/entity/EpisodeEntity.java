package pl.hypeapp.episodie.core.entity;

public interface EpisodeEntity {

    String getEpisodeApiId();

    String getUrl();

    String getName();

    Integer getSeasonNumber();

    Integer getEpisodeNumber();

    String getPremiereDate();

    Integer getRuntime();

    String getImageMedium();

    String getImageOriginal();

    String getSummary();

}
