package pl.hypeapp.core.entity.api.tvmaze;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
public class Embedded {

    private List<SeasonRemote> seasons;

    private List<EpisodeRemote> episodes;

}
