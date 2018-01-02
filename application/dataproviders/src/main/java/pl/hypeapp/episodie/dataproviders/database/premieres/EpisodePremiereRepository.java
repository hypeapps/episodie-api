package pl.hypeapp.episodie.dataproviders.database.premieres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.hypeapp.episodie.core.entity.database.EpisodePremiereLocal;

import java.util.Date;

public interface EpisodePremiereRepository extends JpaRepository<EpisodePremiereLocal, String> {

    @Query("SELECT premiere FROM EpisodePremiereLocal premiere WHERE premiere.premiereDate >= :date ORDER BY premiere.premiereDate ASC")
    Page<EpisodePremiereLocal> findFromPremiereDateOrderByPremiereDateAsc(@Param("date") Date date, Pageable pageableRequest);

}
