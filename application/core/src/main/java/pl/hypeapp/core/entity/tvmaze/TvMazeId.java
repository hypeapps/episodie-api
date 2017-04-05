package pl.hypeapp.core.entity.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TvMazeId {
    @JsonProperty("id")
    public String id;

}
