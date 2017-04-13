package pl.hypeapp.episodie.dataproviders.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.hypeapp.core.entity.database.TvShowLocal;

public interface TvShowRepository extends JpaRepository<TvShowLocal, Integer> {

    @Query("SELECT CASE  WHEN count(tv)> 0 THEN true ELSE false END FROM TvShowLocal tv WHERE tv.tvShowApiId = :id")
    boolean existsByTvShowApiId(@Param("id") String tvShowApiId);

}
