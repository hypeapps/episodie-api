package pl.hypeapp.core.entity.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
public class TvShow {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public String status;
    @JsonProperty("runtime")
    public Integer runtime;
    @JsonProperty("premiered")
    public String premiered;
    @JsonProperty("externals")
    public Externals externals;
    @JsonProperty("image")
    public Image image;
    @JsonProperty("summary")
    public String summary;
    @JsonProperty("updated")
    public Integer updated;
    @JsonProperty("_embedded")
    public Embedded embedded;
}
