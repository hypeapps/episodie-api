package pl.hypeapp.core.entity.database;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.core.entity.TvShowEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@Entity
@Table(name = "TvShows")
public class TvShowLocal implements TvShowEntity {

    @Id
    @GeneratedValue
    public Integer id;

    public Integer tvShowId;

    public String name;

    public String status;

    public Integer runtime;

    public String premiered;

    public String summary;

    public String coverOriginal;

    public String coverMedium;

    public Integer updated;

    @Column(name = "seasons")
    @OneToMany
    @ElementCollection(targetClass = SeasonLocal.class)
    public List<SeasonLocal> seasons;

    @Column(name = "episodes")
    @OneToMany
    @ElementCollection(targetClass = EpisodeLocal.class)
    public List<EpisodeLocal> episodes;

//    @Override
//    public List getSeasons() {
//        return seasons;
//    }
//
//    @Override
//    public List getEpisodes() {
//        return episodes;
//    }
}
