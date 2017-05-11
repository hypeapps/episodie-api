package pl.hypeapp.episodie.core.entity;

import java.util.Map;

@FunctionalInterface
public interface TvShowsUpdatesEntity {

    Map<String, Integer> getUpdates();

}
