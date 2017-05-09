package pl.hypeapp.core.entity.database;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;
import pl.hypeapp.core.entity.TvShowEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@Builder
@Entity
@Indexed
@Table(name = "tv_shows")
@AnalyzerDef(name = "tvShowNameAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English")
                })
        })
public class TvShowLocal implements TvShowEntity<SeasonLocal, EpisodeLocal> {

    @Id
    @Column(name = "tv_show_api_id")
    private String tvShowApiId;

    private String imdbId;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Analyzer(definition = "tvShowNameAnalyzer")
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
