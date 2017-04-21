package pl.hypeapp.core.entity.api.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.core.entity.EpisodeEntity;

@Data
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeRemote implements EpisodeEntity {

    @JsonProperty("id")
    private String episodeApiId;

    private String url;

    private String name;

    @JsonProperty("season")
    private Integer seasonNumber;
    @JsonProperty("number")
    private Integer episodeNumber;
    @JsonProperty("airstamp")
    private String airStamp;

    private Integer runtime;

    private Image image;

    private String summary;

    @Override
    public String getImageMedium() {
        if (image != null) {
            return image.getMedium();
        }
        return null;
    }

    @Override
    public String getImageOriginal() {
        if (image != null) {
            return image.getOriginal();
        }
        return null;
    }

}
