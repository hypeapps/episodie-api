package pl.hypeapp.core.entity.database;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;
import pl.hypeapp.core.entity.EpisodeEntity;

import javax.persistence.*;

@Data
@Builder
@Setter(AccessLevel.NONE)
@Entity
@Table(name = "episodes")
public class EpisodeLocal implements EpisodeEntity {

    @Id
    @Column(name = "episode_api_id")
    private String episodeApiId;

    private String url;

    private String name;

    private Integer seasonNumber;

    private Integer episodeNumber;

    private String airStamp;

    private Integer runtime;

    private String imageMedium;

    private String imageOriginal;

    @Lob
    @Column(length = 100000)
    private String summary;

    @Tolerate
    public EpisodeLocal() {
        //Need to be empty because of JPA and @Tolerate for lombok.
    }

}
