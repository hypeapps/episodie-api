package pl.hypeapp.core.entity.database;

import lombok.*;
import lombok.experimental.Tolerate;
import pl.hypeapp.core.entity.TvShowEntity;

import javax.persistence.*;
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

    public String status;

    private Integer runtime;

    private Integer fullRuntime;

    private String premiered;

    @Lob
    @Column(length = 100000)
    private String summary;

    private String imageMedium;

    private String imageOriginal;

    private Integer updated;

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
    }

}
