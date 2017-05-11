package pl.hypeapp.episodie.dataproviders.database.mostpopular;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;

import java.util.List;

public interface TvShowMostPopularRepository extends Repository<TvShowMostPopularLocal, Integer> {

    @Query("SELECT tvShow FROM TvShowLocal tvShow WHERE tvShow.tvShowApiId IN :mostPopularIds")
    List<TvShowLocal> getTopListTvShows(@Param("mostPopularIds") List<String> mostPopularIds);

    TvShowMostPopularLocal save(TvShowMostPopularLocal tvShowMostPopularLocal);

    Page<TvShowMostPopularLocal> findAll(Pageable pageableRequest);

}
