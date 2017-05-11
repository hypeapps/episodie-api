package pl.hypeapp.episodie.core.entity.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.hypeapp.episodie.core.entity.TvShowsUpdatesEntity;

import java.util.Map;

@Data
@AllArgsConstructor
public class TvShowsUpdatesLocal implements TvShowsUpdatesEntity {

    private Map<String, Integer> updates;

}
