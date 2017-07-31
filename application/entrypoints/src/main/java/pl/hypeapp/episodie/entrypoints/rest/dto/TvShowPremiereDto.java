package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter(AccessLevel.NONE)
public class TvShowPremiereDto {

    private String tvShowApiId;

    private String imdbId;

    private String name;

    private String officialSite;

    private String network;

    private String genre;

    private Integer runtime;

    private Integer fullRuntime;

    private String premiere;

    private String summary;

    private String imageMedium;

    private String imageOriginal;

}
