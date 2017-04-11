package pl.hypeapp.core.entity.api.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.core.entity.EpisodeEntity;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvShowRemote {
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

    public List getSeasons() {
        return embedded.getSeasons();
    }

    public List<EpisodeEntity> getEpisodes() {
        return embedded.getEpisodes();
    }
}
