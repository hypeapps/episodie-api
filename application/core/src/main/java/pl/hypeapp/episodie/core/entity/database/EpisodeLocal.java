package pl.hypeapp.episodie.core.entity.database;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.hypeapp.episodie.core.entity.EpisodeEntity;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdEntity;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedEntity;

    @Lob
    @Column(length = 100000)
    private String summary;

    @Tolerate
    public EpisodeLocal() {
        //Need to be empty because of JPA and @Tolerate for lombok.
    }

}
