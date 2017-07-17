package pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres;

import com.google.common.collect.Ordering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.TvShowPremiereBundle;
import pl.hypeapp.episodie.core.entity.database.PremiereLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetPremieresUseCase {

    private final GetPremieres getPremieres;

    public GetPremieresUseCase(GetPremieres getPremieres) {
        this.getPremieres = getPremieres;
    }

    public Page<TvShowPremiereBundle> getPremieres(Pageable pageableRequest) {
        Page<PremiereLocal> premieres = getPremieres.getPremieres(pageableRequest);
        if (pageableRequest.getPageNumber() >= premieres.getTotalPages()) {
            throw new ResourceNotFoundException();
        }
        List<String> premiereIds = getPremieresIds(premieres);
        List<TvShowLocal> tvShowPremieres = getPremieres.getPremieresTvShows(premiereIds);
        orderTvShowsById(premiereIds, tvShowPremieres);
        return new PageImpl<>(mapToBundle(premieres.getContent(), tvShowPremieres), pageableRequest, premieres.getTotalElements());
    }

    private List<String> getPremieresIds(Page<PremiereLocal> premieres) {
        return premieres.getContent().stream()
                .map(PremiereLocal::getTvShowApiId)
                .collect(Collectors.toList());
    }

    private void orderTvShowsById(List<String> premieresIds, List<TvShowLocal> tvShowPremieres) {
        Ordering<String> idOrdering = Ordering.explicit(premieresIds);
        tvShowPremieres.sort((o1, o2) -> idOrdering.compare(o1.getTvShowApiId(), o2.getTvShowApiId()));
    }

    private List<TvShowPremiereBundle> mapToBundle(List<PremiereLocal> premieres, List<TvShowLocal> tvShows) {
        List<TvShowPremiereBundle> bundle = new ArrayList<>();
        for (int i = 0; i < tvShows.size(); i++) {
            bundle.add(new TvShowPremiereBundle(tvShows.get(i), premieres.get(i)));
        }
        return bundle;
    }

}
