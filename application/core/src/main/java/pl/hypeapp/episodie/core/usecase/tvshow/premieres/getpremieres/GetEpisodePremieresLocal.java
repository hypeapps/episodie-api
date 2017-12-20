package pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.EpisodePremiereLocal;

import java.util.Date;

public interface GetEpisodePremieresLocal {

    Page<EpisodePremiereLocal> getEpisodePremieres(Pageable pageable, Date date);

}
