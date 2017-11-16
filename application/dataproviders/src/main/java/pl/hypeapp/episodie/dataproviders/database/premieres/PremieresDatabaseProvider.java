package pl.hypeapp.episodie.dataproviders.database.premieres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.PremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectimdbtvshowpremieres.InsertPremieres;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetPremieres;

import java.util.Date;
import java.util.List;

public class PremieresDatabaseProvider implements InsertPremieres, GetPremieres {

    private static final Logger LOGGER = LoggerFactory.getLogger(PremieresDatabaseProvider.class);

    private final TvShowPremieresRepository tvShowPremieresRepository;

    public PremieresDatabaseProvider(TvShowPremieresRepository tvShowPremieresRepository) {
        this.tvShowPremieresRepository = tvShowPremieresRepository;
    }

    @Override
    public void insert(List<PremiereLocal> premieresLocal) {
        tvShowPremieresRepository.deleteAll();
        premieresLocal.forEach(premiereLocal -> {
            tvShowPremieresRepository.save(premiereLocal);
            LOGGER.info("Inserted to premieres: " + premiereLocal.getTvShowApiId() + " : " + premiereLocal.getPremiereDate());
        });
        tvShowPremieresRepository.save(premieresLocal);
    }

    @Override
    public Page<PremiereLocal> getPremieres(Pageable pageable, Date fromDate) {
        return tvShowPremieresRepository.findFromPremiereDateOrderByPremiereDateAsc(fromDate, pageable);
    }

    @Override
    public List<TvShowLocal> getPremieresTvShows(List<String> premieresIds) {
        return tvShowPremieresRepository.getPremieresTvShows(premieresIds);
    }

}
