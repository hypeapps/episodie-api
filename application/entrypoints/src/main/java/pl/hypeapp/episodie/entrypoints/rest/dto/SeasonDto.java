package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Setter(AccessLevel.NONE)
public class SeasonDto {

    private String seasonApiId;

    private String tvShowApiId;

    private String url;

    private Integer seasonNumber;

    private Integer episodeOrder;

    private Integer runtime;

    private String premiereDate;

    private String endDate;

    private String summary;

    private String imageMedium;

    private List<EpisodeDto> episodes;

}
