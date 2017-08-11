package pl.hypeapp.episodie.entrypoints.rest.dto.mapper;

import pl.hypeapp.episodie.core.entity.TvShowPremiereBundle;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.SeasonLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.entrypoints.rest.dto.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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

    public List<SeasonDto> tvShowLocalToSeasonsDto(TvShowLocal tvShowLocal) {
        return tvShowLocal.getSeasons().stream()
                .filter(this::isSeasonAfterPremiereDate)
                .map(seasonLocal -> SeasonDto.builder()
                        .seasonApiId(seasonLocal.getSeasonApiId())
                        .episodeOrder(seasonLocal.getEpisodeOrder())
                        .seasonNumber(seasonLocal.getSeasonNumber())
                        .endDate(seasonLocal.getEndDate())
                        .premiereDate(seasonLocal.getPremiereDate())
                        .url(seasonLocal.getUrl())
                        .summary(seasonLocal.getUrl())
                        .episodes(tvShowLocalToEpisodesDto(tvShowLocal, seasonLocal.getSeasonNumber()))
                        .runtime(calculateActualRuntime(tvShowLocal, seasonLocal.getSeasonNumber()))
                        .imageMedium(seasonLocal.getImageMedium())
                        .build())
                .sorted(sortSeasonsAsc())
                .collect(Collectors.toList());
    }

    public List<EpisodeDto> tvShowLocalToEpisodesDto(TvShowLocal tvShowLocal, Integer seasonNumber) {
        return tvShowLocal.getEpisodes().stream()
                .filter(episodeLocal -> episodeLocal.getSeasonNumber().equals(seasonNumber))
                .filter(this::isEpisodeAfterPremiereDate)
                .map(episodeLocal -> episodeLocalToDto.apply(episodeLocal))
                .sorted(sortEpisodesAsc())
                .collect(Collectors.toList());
    }

    public Function<EpisodeLocal, EpisodeDto> episodeLocalToDto = episodeLocal -> EpisodeDto.builder()
            .episodeApiId(episodeLocal.getEpisodeApiId())
            .url(episodeLocal.getUrl())
            .name(episodeLocal.getName())
            .seasonNumber(episodeLocal.getSeasonNumber())
            .episodeNumber(episodeLocal.getEpisodeNumber())
            .premiereDate(episodeLocal.getPremiereDate())
            .runtime(episodeLocal.getRuntime())
            .imageMedium(episodeLocal.getImageMedium())
            .imageOriginal(episodeLocal.getImageOriginal())
            .summary(episodeLocal.getSummary())
            .build();

    private Integer calculateActualRuntime(TvShowLocal tvShowLocal, Integer seasonNumber) {
        return tvShowLocal.getEpisodes().stream()
                .filter(episodeLocal -> episodeLocal.getSeasonNumber().equals(seasonNumber))
                .filter(this::isEpisodeAfterPremiereDate)
                .mapToInt(EpisodeLocal::getRuntime)
                .sum();
    }

    private Comparator<SeasonDto> sortSeasonsAsc() {
        return (o1, o2) -> {
            if (o1.getSeasonNumber() > o2.getSeasonNumber()) return 1;
            else return -1;
        };
    }

    private Comparator<EpisodeDto> sortEpisodesAsc() {
        return (o1, o2) -> {
            if (o1.getEpisodeNumber() > o2.getEpisodeNumber()) return 1;
            else return -1;
        };
    }

    private boolean isEpisodeAfterPremiereDate(EpisodeLocal episodeLocal) {
        return episodeLocal.getPremiereDate() != null &&
                LocalDate.parse(episodeLocal.getPremiereDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).isBefore(LocalDate.now(ZoneId.of("Europe/Warsaw")));
    }

    private boolean isSeasonAfterPremiereDate(SeasonLocal seasonLocal) {
        return seasonLocal.getPremiereDate() != null &&
                LocalDate.parse(seasonLocal.getPremiereDate(), DateTimeFormatter.ISO_LOCAL_DATE).isBefore(LocalDate.now(ZoneId.of("Europe/Warsaw")));
    }

}
