package pl.hypeapp.episodie.core.entity.database;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@Entity
@Table(name = "tv_show_most_popular")
public class TvShowMostPopularLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer position;

    private String tvShowApiId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdEntity;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedEntity;

    @Tolerate
    public TvShowMostPopularLocal() {
        //Need to be empty because of JPA and @Tolerate for lombok.
    }

    @Tolerate
    public TvShowMostPopularLocal(Integer position, String tvShowApiId) {
        this.position = position;
        this.tvShowApiId = tvShowApiId;
    }

}
