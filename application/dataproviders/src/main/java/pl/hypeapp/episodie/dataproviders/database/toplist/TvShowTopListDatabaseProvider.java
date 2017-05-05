package pl.hypeapp.episodie.dataproviders.database.toplist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.entity.database.TvShowTopListLocal;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.InsertTvShowToTopList;
import pl.hypeapp.core.usecase.tvshow.toplist.gettoplist.GetTvShowTopList;

import java.util.List;

@Transactional
public class TvShowTopListDatabaseProvider implements InsertTvShowToTopList, GetTvShowTopList {

    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowTopListDatabaseProvider.class);

    private final TvShowTopListRepository tvShowTopListRepository;

    public TvShowTopListDatabaseProvider(TvShowTopListRepository tvShowTopListRepository) {
        this.tvShowTopListRepository = tvShowTopListRepository;
    }

    @Override
    public void insert(TvShowTopListLocal tvShowTopListLocal) {
        tvShowTopListRepository.save(tvShowTopListLocal);
        LOGGER.info("Inserted to toplist: " + tvShowTopListLocal.getPosition() + " : " + tvShowTopListLocal.getTvShowApiId());
    }

    @Override
    public Page<TvShowTopListLocal> getTopList(Pageable pageableRequest) {
        return tvShowTopListRepository.findAll(pageableRequest);
    }

    @Override
    public List<TvShowLocal> getTopListTvShows(List<String> topListIds) {
        return tvShowTopListRepository.getTopListTvShows(topListIds);
    }

}
