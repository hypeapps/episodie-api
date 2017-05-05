package pl.hypeapp.episodie.dataproviders.database.toplist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.entity.database.TvShowTopListLocal;

import java.util.List;

public interface TvShowTopListRepository extends Repository<TvShowTopListLocal, Integer> {

    @Query("SELECT tvShow FROM TvShowLocal tvShow WHERE tvShow.tvShowApiId IN :topListIds")
    List<TvShowLocal> getTopListTvShows(@Param("topListIds") List<String> topListIds);

    TvShowTopListLocal save(TvShowTopListLocal tvShowTopListLocal);

    Page<TvShowTopListLocal> findAll(Pageable pageableRequest);

}
