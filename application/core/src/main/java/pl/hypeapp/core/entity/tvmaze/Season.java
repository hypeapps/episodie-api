package pl.hypeapp.core.entity.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Season {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("number")
    public Integer number;
    @JsonProperty("name")
    public String name;
    @JsonProperty("episodeOrder")
    public Integer episodeOrder;
    @JsonProperty("premiereDate")
    public String premiereDate;
    @JsonProperty("endDate")
    public String endDate;
    @JsonProperty("summary")
    public String summary;
}
