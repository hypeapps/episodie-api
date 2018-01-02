package pl.hypeapp.episodie.core.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.EpisodePremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class EpisodePremiereBundle {

    private TvShowLocal tvShowLocal;

    private EpisodeLocal episodeLocal;

    private EpisodePremiereLocal episodePremiereLocal;

}
