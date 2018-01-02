package pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres;

import pl.hypeapp.episodie.core.entity.database.EpisodePremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowPremiereLocal;

import java.util.List;

public interface InsertPremieres {

    void insert(List<TvShowPremiereLocal> tvShowPremiereLocal);

    void insertEpisodePremiere(EpisodePremiereLocal episodePremiereLocal);

}
