package pl.hypeapp.core.entity.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Externals {
    @JsonProperty("tvrage")
    public Integer tvrageId;
    @JsonProperty("thetvdb")
    public Integer thetvdbId;
    @JsonProperty("imdb")
    public String imdbId;
}