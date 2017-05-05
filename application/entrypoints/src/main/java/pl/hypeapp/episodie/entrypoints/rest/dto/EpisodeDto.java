package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter(AccessLevel.NONE)
public class EpisodeDto {

    private String episodeApiId;

    private String url;

    private String name;

    private Integer seasonNumber;

    private Integer episodeNumber;

    private String airStamp;

    private Integer runtime;

    private String imageMedium;

    private String imageOriginal;

    private String summary;

}
