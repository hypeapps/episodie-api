package pl.hypeapp.episodie.core.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import pl.hypeapp.episodie.core.entity.database.PremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class TvShowPremiereBundle {

    TvShowLocal tvShowLocal;

    PremiereLocal premiereLocal;

}
