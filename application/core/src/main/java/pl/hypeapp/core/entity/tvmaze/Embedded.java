package pl.hypeapp.core.entity.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Embedded {
    @JsonProperty("seasons")
    public List<Season> seasons;
    @JsonProperty("episodes")
    public List<Episode> episodes;
}
