package pl.hypeapp.episodie.core.entity;

public interface SeasonEntity {

    String getSeasonApiId();

    String getUrl();

    Integer getSeasonNumber();

    Integer getEpisodeOrder();

    String getPremiereDate();

    String getEndDate();

    String getSummary();

}
