package pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectimdbtvshowpremieres;

import pl.hypeapp.episodie.core.entity.database.PremiereLocal;

import java.util.List;

@FunctionalInterface
public interface InsertPremieres {

    void insert(List<PremiereLocal> premiereLocal);

}
