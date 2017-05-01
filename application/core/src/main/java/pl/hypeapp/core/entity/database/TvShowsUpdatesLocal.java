package pl.hypeapp.core.entity.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.hypeapp.core.entity.TvShowsUpdatesEntity;

import java.util.Map;

@Data
@AllArgsConstructor
public class TvShowsUpdatesLocal implements TvShowsUpdatesEntity {

    private Map<String, Integer> updates;

}
