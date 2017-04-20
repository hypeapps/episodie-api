package pl.hypeapp.core.entity.api.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class Externals {

    @JsonProperty("imdb")
    private String imdbId;

}