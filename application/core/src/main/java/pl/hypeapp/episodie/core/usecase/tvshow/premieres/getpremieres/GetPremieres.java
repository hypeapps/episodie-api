package pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.PremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

import java.util.List;

public interface GetPremieres {

    Page<PremiereLocal> getPremieres(Pageable pageable);

    List<TvShowLocal> getPremieresTvShows(List<String> premieresIds);

}
