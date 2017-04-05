package pl.hypeapp.core.entity.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Episode {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;
    @JsonProperty("season")
    public Integer season;
    @JsonProperty("number")
    public Integer number;
    @JsonProperty("airstamp")
    public String airstamp;
    @JsonProperty("runtime")
    public Integer runtime;
    @JsonProperty("image")
    public Image image;
    @JsonProperty("summary")
    public String summary;
}
