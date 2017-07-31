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

    private String officialSite;

    private Integer runtime;

    private String premiered;

    private Network network;

    private String[] genres;

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
    public List<SeasonRemote> getSeasons() {
        return embedded.getSeasons();
    }

    @Override
    public List<EpisodeRemote> getEpisodes() {
        return embedded.getEpisodes();
    }

    @Override
    public String getNetworkName() {
        if (network != null) {
            return network.getName();
        } else {
            return null;
        }
    }

    @Override
    public String getGenre() {
        if (genres != null) {
            return String.join("/", genres);
        } else {
            return null;
        }
    }

    @Override
    public String getImageMedium() {
        if (image != null) {
            return image.getMedium();
        } else {
            return null;
        }
    }

    @Override
    public String getImageOriginal() {
        if (image != null) {
            return image.getOriginal();
        } else {
            return null;
        }
    }

    @Override
    public Integer getFullRuntime() {
        return getEpisodes()
                .stream()
                .mapToInt(EpisodeRemote::getRuntime)
                .sum();
    }

}
