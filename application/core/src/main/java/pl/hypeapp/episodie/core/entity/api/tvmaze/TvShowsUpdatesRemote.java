package pl.hypeapp.episodie.core.entity.api.tvmaze;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.episodie.core.entity.TvShowsUpdatesEntity;

import java.util.Map;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class TvShowsUpdatesRemote implements TvShowsUpdatesEntity {

    private Map<String, Integer> updates;

}
