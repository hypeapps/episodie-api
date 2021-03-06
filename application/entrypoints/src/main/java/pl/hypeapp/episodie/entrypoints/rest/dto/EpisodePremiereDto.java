package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter(AccessLevel.NONE)
public class EpisodePremiereDto {

    private String episodeApiId;

    private String url;

    private String name;

    private Integer seasonNumber;

    private Integer episodeNumber;

    private String premiereDate;

    private Integer runtime;

    private String imageMedium;

    private String imageOriginal;

    private String summary;

    private TvShowDto tvShow;
}
