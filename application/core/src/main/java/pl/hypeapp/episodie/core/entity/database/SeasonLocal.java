package pl.hypeapp.episodie.core.entity.database;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.hypeapp.episodie.core.entity.SeasonEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Setter(AccessLevel.NONE)
@Builder
@Entity
@Table(name = "seasons")
public class SeasonLocal implements SeasonEntity {

    @Id
    @Column(name = "season_api_id")
    private String seasonApiId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdEntity;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedEntity;

    @Lob
    @Column(length = 100000)
    private String url;

    private Integer seasonNumber;

    private Integer episodeOrder;

    private Integer runtime;

    private String premiereDate;

    private String endDate;

    private String imageMedium;

    private String imageOriginal;

    @Lob
    @Column(length = 100000)
    private String summary;

    @Tolerate
    public SeasonLocal() {
        //Need to be empty because of JPA and @Tolerate for lombok.
    }

}
