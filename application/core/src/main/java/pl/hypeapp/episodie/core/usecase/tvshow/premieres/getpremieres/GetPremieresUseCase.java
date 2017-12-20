package pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres;

import com.google.common.collect.Ordering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.EpisodePremiereBundle;
import pl.hypeapp.episodie.core.entity.TvShowPremiereBundle;
import pl.hypeapp.episodie.core.entity.api.tvmaze.EpisodePremiereRemote;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.EpisodePremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowPremiereLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.GetTvShowUseCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GetPremieresUseCase {

    private final GetTvShowPremieres getTvShowPremieres;

    private final GetEpisodePremieresRemote getEpisodePremieresRemote;

    private final GetEpisodePremieresLocal getEpisodePremieresLocal;

    private final GetTvShowUseCase getTvShowUseCase;

    private static final Logger LOGGER = Logger.getLogger("GetPremiereUseCase");

    public GetPremieresUseCase(GetTvShowPremieres getTvShowPremieres, GetEpisodePremieresRemote getEpisodePremieresRemote,
                               GetEpisodePremieresLocal getEpisodePremieresLocal, GetTvShowUseCase getTvShowUseCase) {
        this.getTvShowPremieres = getTvShowPremieres;
        this.getEpisodePremieresRemote = getEpisodePremieresRemote;
        this.getEpisodePremieresLocal = getEpisodePremieresLocal;
        this.getTvShowUseCase = getTvShowUseCase;
    }

    public Page<TvShowPremiereBundle> getTvShowPremieres(Pageable pageableRequest, Date date) {
        Page<TvShowPremiereLocal> premieres = getTvShowPremieres.getPremieres(pageableRequest, date);
        if (pageableRequest.getPageNumber() >= premieres.getTotalPages()) {
            throw new ResourceNotFoundException();
        }
        List<String> premiereIds = getTvShowPremieresIds(premieres);
        List<TvShowLocal> tvShowPremieres = getTvShowPremieres.getPremieresTvShows(premiereIds);
        orderTvShowsById(premiereIds, tvShowPremieres);
        return new PageImpl<>(mapToBundle(premieres.getContent(), tvShowPremieres), pageableRequest, premieres.getTotalElements());
    }

    public List<TvShowPremiereBundle> getTvShowPremieresByDate(Date date) {
        List<TvShowPremiereLocal> premieresLocal = getTvShowPremieres.getPremieres(date);
        if (!premieresLocal.isEmpty()) {
            List<String> premieresIds = getTvShowPremieresIds(premieresLocal);
            List<TvShowLocal> tvShowPremieres = getTvShowPremieres.getPremieresTvShows(premieresIds);
            orderTvShowsById(premieresIds, tvShowPremieres);
            return mapToBundle(premieresLocal, tvShowPremieres);
        }
        return null;
    }

    public List<EpisodePremiereRemote> getEpisodePremieresRemote(Date date) {
        return getEpisodePremieresRemote.getEpisodePremieres(date);
    }

    public Page<EpisodePremiereBundle> getEpisodePremieresLocal(Pageable pageableRequest, Date date) {
        Page<EpisodePremiereLocal> episodePremieres = getEpisodePremieresLocal.getEpisodePremieres(pageableRequest, date);
        if (pageableRequest.getPageNumber() >= episodePremieres.getTotalPages()) {
            throw new ResourceNotFoundException();
        }
        List<EpisodePremiereBundle> episodePremiereBundle = new ArrayList<>();
        episodePremieres.forEach(episodePremiereLocal -> {
            TvShowLocal tvShowLocal = getTvShowUseCase.getTvShowFromLocal(episodePremiereLocal.getTvShowApiId());
            try {
                EpisodeLocal episodeLocal = tvShowLocal.getEpisodes().stream()
                        .filter(episode -> episode.getEpisodeApiId().equals(episodePremiereLocal.getEpisodeId()))
                        .findFirst()
                        .orElseThrow(ResourceNotFoundException::new);
                episodePremiereBundle.add(new EpisodePremiereBundle(tvShowLocal, episodeLocal, episodePremiereLocal));
            } catch (ResourceNotFoundException e) {
                LOGGER.log(Level.WARNING, "Misssing episode. Skiping.");
            }
        });
        return new PageImpl<>(episodePremiereBundle, pageableRequest, episodePremieres.getTotalElements());
    }

    private List<String> getTvShowPremieresIds(Page<TvShowPremiereLocal> premieres) {
        return premieres.getContent().stream()
                .map(TvShowPremiereLocal::getTvShowApiId)
                .collect(Collectors.toList());
    }

    private List<String> getTvShowPremieresIds(List<TvShowPremiereLocal> premieres) {
        return premieres.stream()
                .map(TvShowPremiereLocal::getTvShowApiId)
                .collect(Collectors.toList());
    }

    private void orderTvShowsById(List<String> premieresIds, List<TvShowLocal> tvShowPremieres) {
        Ordering<String> idOrdering = Ordering.explicit(premieresIds);
        tvShowPremieres.sort((o1, o2) -> idOrdering.compare(o1.getTvShowApiId(), o2.getTvShowApiId()));
    }

    private List<TvShowPremiereBundle> mapToBundle(List<TvShowPremiereLocal> premieres, List<TvShowLocal> tvShows) {
        List<TvShowPremiereBundle> bundle = new ArrayList<>();
        for (int i = 0; i < tvShows.size(); i++) {
            bundle.add(new TvShowPremiereBundle(tvShows.get(i), premieres.get(i)));
        }
        return bundle;
    }

}
