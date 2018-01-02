package pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres;

import pl.hypeapp.episodie.core.entity.api.tvmaze.EpisodePremiereRemote;

import java.util.Date;
import java.util.List;

public interface GetEpisodePremieresRemote {

    List<EpisodePremiereRemote> getEpisodePremieres(Date date);

}
