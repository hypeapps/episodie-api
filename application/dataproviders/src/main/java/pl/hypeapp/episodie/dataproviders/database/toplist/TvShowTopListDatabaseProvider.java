package pl.hypeapp.episodie.dataproviders.database.toplist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import pl.hypeapp.core.entity.database.TvShowTopListLocal;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.InsertTvShowToTopList;
import pl.hypeapp.core.usecase.tvshow.toplist.gettoplist.GetTvShowTopList;
import pl.hypeapp.episodie.dataproviders.database.tvshow.TvShowRepository;

@Transactional
public class TvShowTopListDatabaseProvider implements InsertTvShowToTopList, GetTvShowTopList {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowTopListDatabaseProvider.class);
    private final TvShowRepository tvShowRepository;
    private final TvShowTopListRepository tvShowTopListRepository;

    public TvShowTopListDatabaseProvider(TvShowRepository tvShowRepository, TvShowTopListRepository tvShowTopListRepository) {
        this.tvShowRepository = tvShowRepository;
        this.tvShowTopListRepository = tvShowTopListRepository;
    }

    @Override
    public void insert(TvShowTopListLocal tvShowTopListLocal) {
        tvShowTopListRepository.save(tvShowTopListLocal);
        LOGGER.info("Inserted to toplist: " + tvShowTopListLocal.getPosition() + " : " + tvShowTopListLocal.getTvShowApiId());
    }

}
