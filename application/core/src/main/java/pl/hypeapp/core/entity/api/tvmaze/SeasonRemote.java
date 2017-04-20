package pl.hypeapp.core.entity.api.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.core.entity.SeasonEntity;

@Data
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonRemote implements SeasonEntity {

    @JsonProperty("id")
    private String seasonApiId;

    private String url;

    @JsonProperty("number")
    private Integer seasonNumber;

    private String name;

    private Integer episodeOrder;

    private String premiereDate;

    private String endDate;

    private String summary;

}
