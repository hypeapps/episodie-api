package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter(AccessLevel.NONE)
public class SearchNameResultDto {

    private String tvShowApiId;

    private String name;

    private Integer episodeOrder;

    private Integer fullRuntime;

    private String imageMedium;

    private String imageOriginal;

}
