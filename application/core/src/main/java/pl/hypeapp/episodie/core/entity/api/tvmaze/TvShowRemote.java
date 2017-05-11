package pl.hypeapp.episodie.core.entity.api.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.episodie.core.entity.TvShowEntity;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvShowRemote implements TvShowEntity<SeasonRemote, EpisodeRemote> {

    @JsonProperty("id")
    private String tvShowApiId;

    private String url;

    private String name;

    private String status;

    private Integer runtime;

    private String premiered;

    private Externals externals;

    private Image image;

    private String summary;

    private Integer updated;
    @JsonProperty("_embedded")
    private Embedded embedded;

    @Override
    public String getImdbId() {
        return externals.getImdbId();
    }

    @Override
    public String getImageMedium() {
        return image.getMedium();
    }

    @Override
    public String getImageOriginal() {
        return image.getOriginal();
    }

    @Override
    public List<SeasonRemote> getSeasons() {
        return embedded.getSeasons();
    }

    @Override
    public List<EpisodeRemote> getEpisodes() {
        return embedded.getEpisodes();
    }

    @Override
    public Integer getFullRuntime() {
        return getEpisodes()
                .stream()
                .mapToInt(EpisodeRemote::getRuntime)
                .sum();
    }

}
