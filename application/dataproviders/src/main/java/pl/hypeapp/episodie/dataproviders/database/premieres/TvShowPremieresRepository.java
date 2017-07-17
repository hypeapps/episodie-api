package pl.hypeapp.episodie.dataproviders.database.premieres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.hypeapp.episodie.core.entity.database.PremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

import java.util.List;

public interface TvShowPremieresRepository extends CrudRepository<PremiereLocal, Integer> {

    @Query("SELECT tvShow FROM TvShowLocal tvShow WHERE tvShow.tvShowApiId IN :topListIds")
    List<TvShowLocal> getPremieresTvShows(@Param("topListIds") List<String> topListIds);

    Page<PremiereLocal> findAllByOrderByPremiereDateAsc(Pageable pageableRequest);

}
