package pl.hypeapp.core.entity.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty("medium")
    public String medium;
    @JsonProperty("original")
    public String original;
}