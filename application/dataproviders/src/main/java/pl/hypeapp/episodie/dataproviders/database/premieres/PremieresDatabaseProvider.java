package pl.hypeapp.episodie.dataproviders.database.premieres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.EpisodePremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowPremiereLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.InsertPremieres;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetEpisodePremieresLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetTvShowPremieres;

import java.util.Date;
import java.util.List;

public class PremieresDatabaseProvider implements InsertPremieres, GetTvShowPremieres, GetEpisodePremieresLocal {

    private static final Logger LOGGER = LoggerFactory.getLogger(PremieresDatabaseProvider.class);

    private final TvShowPremieresRepository tvShowPremieresRepository;

    private final EpisodePremiereRepository episodePremiereRepository;

    public PremieresDatabaseProvider(TvShowPremieresRepository tvShowPremieresRepository, EpisodePremiereRepository episodePremiereRepository) {
        this.tvShowPremieresRepository = tvShowPremieresRepository;
        this.episodePremiereRepository = episodePremiereRepository;
    }

    @Override
    public void insert(List<TvShowPremiereLocal> premieresLocal) {
        tvShowPremieresRepository.deleteAll();
        premieresLocal.forEach(premiereLocal -> {
            tvShowPremieresRepository.save(premiereLocal);
            LOGGER.info("Inserted to TV SHOW premieres: " + premiereLocal.getTvShowApiId() + " : " + premiereLocal.getPremiereDate());
        });
        tvShowPremieresRepository.save(premieresLocal);
    }

    @Override
    public void insertEpisodePremiere(EpisodePremiereLocal episodePremiereLocal) {
        LOGGER.info("Inserted to EPISODE premieres: " + episodePremiereLocal.getTvShowApiId() + " : " + episodePremiereLocal.getEpisodeId() + " "
                + episodePremiereLocal.getPremiereDate());
        episodePremiereRepository.save(episodePremiereLocal);
    }

    @Override
    public Page<EpisodePremiereLocal> getEpisodePremieres(Pageable pageable, Date date) {
        return episodePremiereRepository.findFromPremiereDateOrderByPremiereDateAsc(date, pageable);
    }

    @Override
    public List<TvShowPremiereLocal> getPremieres(Date date) {
        return tvShowPremieresRepository.findPremieresByDate(date);
    }

    @Override
    public Page<TvShowPremiereLocal> getPremieres(Pageable pageable, Date fromDate) {
        return tvShowPremieresRepository.findFromPremiereDateOrderByPremiereDateAsc(fromDate, pageable);
    }

    @Override
    public List<TvShowLocal> getPremieresTvShows(List<String> premieresIds) {
        return tvShowPremieresRepository.getPremieresTvShows(premieresIds);
    }

}
