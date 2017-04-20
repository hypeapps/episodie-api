package pl.hypeapp.core.entity.database;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;
import pl.hypeapp.core.entity.SeasonEntity;

import javax.persistence.*;

@Data
@Setter(AccessLevel.NONE)
@Builder
@Entity
@Table(name = "seasons")
public class SeasonLocal implements SeasonEntity {

    @Id
    @Column(name = "season_api_id")
    private String seasonApiId;

    private String url;

    private Integer seasonNumber;

    private Integer episodeOrder;

    private Integer runtime;

    private String premiereDate;

    private String endDate;

    @Lob
    @Column(length = 100000)
    private String summary;

    @Tolerate
    public SeasonLocal() {
    }

}
