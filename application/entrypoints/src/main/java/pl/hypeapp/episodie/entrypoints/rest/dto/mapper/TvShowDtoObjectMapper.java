package pl.hypeapp.episodie.entrypoints.rest.dto.mapper;

import pl.hypeapp.episodie.core.entity.TvShowPremiereBundle;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.entrypoints.rest.dto.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TvShowDtoObjectMapper {

    public Function<TvShowLocal, TvShowDto> tvShowLocalToDto = tvShowLocal -> TvShowDto.builder()
            .tvShowApiId(tvShowLocal.getTvShowApiId())
            .imdbId(tvShowLocal.getImdbId())
            .name(tvShowLocal.getName())
            .officialSite(tvShowLocal.getOfficialSite())
            .genre(tvShowLocal.getGenre())
            .network(tvShowLocal.getNetworkName())
            .status(tvShowLocal.getStatus())
            .runtime(tvShowLocal.getRuntime())
            .fullRuntime(tvShowLocal.getFullRuntime())
            .premiered(tvShowLocal.getPremiered())
            .summary(tvShowLocal.getSummary())
            .imageOriginal(tvShowLocal.getImageOriginal())
            .imageMedium(tvShowLocal.getImageMedium())
            .build();

    public Function<TvShowLocal, TvShowExtendedDto> tvShowLocalToDtoExtended = tvShowLocal -> TvShowExtendedDto.builder()
            .tvShowApiId(tvShowLocal.getTvShowApiId())
            .imdbId(tvShowLocal.getImdbId())
            .name(tvShowLocal.getName())
            .status(tvShowLocal.getStatus())
            .officialSite(tvShowLocal.getOfficialSite())
            .genre(tvShowLocal.getGenre())
            .network(tvShowLocal.getNetworkName())
            .runtime(tvShowLocal.getRuntime())
            .fullRuntime(tvShowLocal.getFullRuntime())
            .premiered(tvShowLocal.getPremiered())
            .summary(tvShowLocal.getSummary())
            .imageOriginal(tvShowLocal.getImageOriginal())
            .imageMedium(tvShowLocal.getImageMedium())
            .seasons(tvShowLocalToSeasonsDto(tvShowLocal))
            .build();

    private List<SeasonDto> tvShowLocalToSeasonsDto(TvShowLocal tvShowLocal) {
        return tvShowLocal.getSeasons().stream()
                .filter(seasonLocal -> seasonLocal.getPremiereDate() != null)
                .map(seasonLocal -> SeasonDto.builder()
                        .seasonApiId(seasonLocal.getSeasonApiId())
                        .episodeOrder(seasonLocal.getEpisodeOrder())
                        .seasonNumber(seasonLocal.getSeasonNumber())
                        .endDate(seasonLocal.getEndDate())
                        .premiereDate(seasonLocal.getPremiereDate())
                        .runtime(seasonLocal.getRuntime())
                        .url(seasonLocal.getUrl())
                        .summary(seasonLocal.getUrl())
                        .episodes(tvShowLocalToEpisodesDto(tvShowLocal, seasonLocal.getSeasonNumber()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<EpisodeDto> tvShowLocalToEpisodesDto(TvShowLocal tvShowLocal, Integer seasonNumber) {
        return tvShowLocal.getEpisodes().stream()
                .filter(episodeLocal -> episodeLocal.getSeasonNumber().equals(seasonNumber))
                .map(episodeLocal -> episodeLocalToDto.apply(episodeLocal))
                .collect(Collectors.toList());
    }

    public Function<TvShowPremiereBundle, TvShowPremiereDto> tvShowPremiereBundleToDto = tvShowPremiereBundle ->
            TvShowPremiereDto.builder()
                    .tvShowApiId(tvShowPremiereBundle.getTvShowLocal().getTvShowApiId())
                    .imdbId(tvShowPremiereBundle.getTvShowLocal().getImdbId())
                    .name(tvShowPremiereBundle.getTvShowLocal().getName())
                    .premiere(tvShowPremiereBundle.getPremiereLocal().getPremiereDate())
                    .officialSite(tvShowPremiereBundle.getTvShowLocal().getOfficialSite())
                    .genre(tvShowPremiereBundle.getTvShowLocal().getGenre())
                    .network(tvShowPremiereBundle.getTvShowLocal().getNetworkName())
                    .summary(tvShowPremiereBundle.getTvShowLocal().getSummary())
                    .runtime(tvShowPremiereBundle.getTvShowLocal().getRuntime())
                    .fullRuntime(tvShowPremiereBundle.getTvShowLocal().getFullRuntime())
                    .imageMedium(tvShowPremiereBundle.getTvShowLocal().getImageMedium())
                    .imageOriginal(tvShowPremiereBundle.getTvShowLocal().getImageOriginal())
                    .build();

    private Function<EpisodeLocal, EpisodeDto> episodeLocalToDto = episodeLocal -> EpisodeDto.builder()
            .episodeApiId(episodeLocal.getEpisodeApiId())
            .url(episodeLocal.getUrl())
            .name(episodeLocal.getName())
            .seasonNumber(episodeLocal.getSeasonNumber())
            .episodeNumber(episodeLocal.getEpisodeNumber())
            .airStamp(episodeLocal.getAirStamp())
            .runtime(episodeLocal.getRuntime())
            .imageMedium(episodeLocal.getImageMedium())
            .imageOriginal(episodeLocal.getImageOriginal())
            .summary(episodeLocal.getSummary())
            .build();

}
