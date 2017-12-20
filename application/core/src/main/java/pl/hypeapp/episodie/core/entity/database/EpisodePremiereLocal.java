package pl.hypeapp.episodie.core.entity.database;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@Entity
@Table(name = "episode_premieres")
public class EpisodePremiereLocal {

    @Id
    private String episodeId;

    private String tvShowApiId;

    private Date premiereDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdEntity;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedEntity;

    @Tolerate
    public EpisodePremiereLocal() {
        //Need to be empty because of JPA and @Tolerate for lombok.
    }

    @Tolerate
    public EpisodePremiereLocal(String tvShowApiId, String episodeId, Date premiereDate) {
        this.episodeId = episodeId;
        this.tvShowApiId = tvShowApiId;
        this.premiereDate = premiereDate;
    }

}
