package pl.hypeapp.episodie.core.entity.api.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
public class EpisodePremiereRemote {

    @JsonProperty("id")
    String episodeId;

    Date airstamp;

    @JsonProperty("show")
    Wrapper show;

    @Data
    @Setter(AccessLevel.NONE)
    public class Wrapper {
        @JsonProperty("id")
        String tvShowId;
    }

}
