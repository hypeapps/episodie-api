package pl.hypeapp.episodie.dataproviders.database;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hypeapp.core.entity.tvmaze.TvShow;

public interface TvShowRepository extends JpaRepository<TvShow, String> {
}
