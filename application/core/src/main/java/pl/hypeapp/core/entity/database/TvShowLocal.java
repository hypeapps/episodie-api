package pl.hypeapp.core.entity.database;

import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.hypeapp.core.entity.TvShowEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@Builder
@Entity
@AllArgsConstructor
@Table(name = "tv_shows")
public class TvShowLocal implements TvShowEntity<SeasonLocal, EpisodeLocal> {

    @Id
    @Column(name = "tv_show_api_id")
    private String tvShowApiId;

    private String imdbId;

    private String name;

    private String status;

    private Integer runtime;

    private Integer fullRuntime;

    private String premiered;

    @Lob
    @Column(length = 100000)
    private String summary;

    private String imageMedium;

    private String imageOriginal;

    private Integer updated;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdEntity;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection(targetClass = SeasonLocal.class)
    @JoinColumn(name = "tv_show_api_id")
    private List<SeasonLocal> seasons;

    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection(targetClass = EpisodeLocal.class)
    @JoinColumn(name = "tv_show_api_id")
    private List<EpisodeLocal> episodes;

    @Tolerate
    public TvShowLocal() {
        //Need to be empty because of JPA and @Tolerate for lombok.
    }

}
