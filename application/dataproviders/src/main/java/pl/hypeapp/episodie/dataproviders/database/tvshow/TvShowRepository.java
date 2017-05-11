package pl.hypeapp.episodie.dataproviders.database.tvshow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

import java.util.List;

public interface TvShowRepository extends JpaRepository<TvShowLocal, String> {

    @Query("SELECT CASE WHEN count(tv)> 0 THEN true ELSE false END FROM TvShowLocal tv WHERE tv.tvShowApiId = :id")
    boolean doesTvShowExist(@Param("id") String tvMazeId);

    @Query("SELECT tv.tvShowApiId, tv.updated FROM TvShowLocal tv")
    List<Object[]> getUpdates();

}
