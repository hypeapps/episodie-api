package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EpisodeDto {

    private String episodeApiId;

    private String tvShowApiId;

    private String seasonApiId;

    private String url;

    private String name;

    private Integer seasonNumber;

    private Integer episodeNumber;

    private String premiereDate;

    private Integer runtime;

    private String imageMedium;

    private String imageOriginal;

    private String summary;

}
