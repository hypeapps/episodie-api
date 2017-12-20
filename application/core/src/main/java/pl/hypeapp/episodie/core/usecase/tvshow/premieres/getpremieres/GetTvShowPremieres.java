package pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowPremiereLocal;

import java.util.Date;
import java.util.List;

public interface GetTvShowPremieres {

    Page<TvShowPremiereLocal> getPremieres(Pageable pageable, Date fromDate);

    List<TvShowPremiereLocal> getPremieres(Date date);

    List<TvShowLocal> getPremieresTvShows(List<String> premieresIds);

}
