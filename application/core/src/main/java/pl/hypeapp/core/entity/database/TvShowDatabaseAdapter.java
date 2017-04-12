package pl.hypeapp.core.entity.database;

import pl.hypeapp.core.entity.TvShowEntity;
import pl.hypeapp.core.entity.api.tvmaze.EpisodeRemote;
import pl.hypeapp.core.entity.api.tvmaze.SeasonRemote;
import pl.hypeapp.core.entity.api.tvmaze.TvShowRemote;

import java.util.List;
import java.util.stream.Collectors;

public class TvShowDatabaseAdapter implements TvShowEntity<SeasonLocal, EpisodeLocal> {

    private TvShowRemote tvShowRemote;

    public TvShowDatabaseAdapter(TvShowEntity tvShowRemote) {
        this.tvShowRemote = (TvShowRemote) tvShowRemote;
    }

    @Override
    public String getTvShowApiId() {
        return this.tvShowRemote.getTvShowApiId();
    }

    @Override
    public String getImdbId() {
        return this.tvShowRemote.getImdbId();
    }

    @Override
    public String getName() {
        return tvShowRemote.getName();
    }

    @Override
    public String getStatus() {
        return this.tvShowRemote.getStatus();
    }

    @Override
    public Integer getRuntime() {
        return this.tvShowRemote.getRuntime();
    }

    @Override
    public String getPremiered() {
        return this.tvShowRemote.getPremiered();
    }

    @Override
    public String getSummary() {
        return this.tvShowRemote.getSummary();
    }

    @Override
    public String getImageMedium() {
        return this.tvShowRemote.getImageMedium();
    }

    @Override
    public String getImageOriginal() {
        return this.tvShowRemote.getImageOriginal();
    }

    @Override
    public Integer getUpdated() {
        return this.tvShowRemote.getUpdated();
    }

    @Override
    public List<SeasonLocal> getSeasons() {
        return tvShowRemote.getSeasons()
                .stream()
                .map(this::convertToSeasonLocal)
                .collect(Collectors.toList());
    }

    @Override
    public List<EpisodeLocal> getEpisodes() {
        return tvShowRemote.getEpisodes()
                .stream()
                .map(this::convertToEpisodeLocal)
                .collect(Collectors.toList());
    }

    private SeasonLocal convertToSeasonLocal(SeasonRemote seasonRemote) {
        return SeasonLocal.builder()
                .seasonApiId(seasonRemote.getSeasonApiId())
                .url(seasonRemote.getUrl())
                .seasonNumber(seasonRemote.getSeasonNumber())
                .episodeOrder(seasonRemote.getEpisodeOrder())
                .premiereDate(seasonRemote.getPremiereDate())
                .endDate(seasonRemote.getEndDate())
                .summary(seasonRemote.getSummary())
                .build();
    }

    private EpisodeLocal convertToEpisodeLocal(EpisodeRemote episodeRemote) {
        return EpisodeLocal.builder()
                .episodeApiId(episodeRemote.getEpisodeApiId())
                .url(episodeRemote.getUrl())
                .name(episodeRemote.getName())
                .seasonNumber(episodeRemote.getSeasonNumber())
                .episodeNumber(episodeRemote.getEpisodeNumber())
                .airStamp(episodeRemote.getAirStamp())
                .runtime(episodeRemote.getRuntime())
                .imageMedium(episodeRemote.getImageMedium())
                .imageOriginal(episodeRemote.getImageOriginal())
                .summary(episodeRemote.getSummary())
                .build();
    }
}
