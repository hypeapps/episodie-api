package pl.hypeapp.core.entity.database;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@Entity
@Table(name = "tv_show_top_list")
public class TvShowTopListLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer position;

    private String tvShowApiId;

    @Tolerate
    public TvShowTopListLocal() {
        //Need to be empty because of JPA and @Tolerate for lombok.
    }

}
