package pl.hypeapp.episodie.entrypoints.rest.dto.mapper;

import pl.hypeapp.episodie.core.entity.EpisodePremiereBundle;
import pl.hypeapp.episodie.core.entity.TvShowPremiereBundle;
import pl.hypeapp.episodie.core.entity.database.EpisodeLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.entrypoints.rest.dto.EpisodePremiereDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowPremiereDto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PremiereBundleDtoMapper {

    public Function<EpisodePremiereBundle, EpisodePremiereDto> episodePremiereBundleToDto = episodePremiereBundle ->
            EpisodePremiereDto.builder()
                    .episodeApiId(episodePremiereBundle.getEpisodePremiereLocal().getEpisodeId())
                    .url(episodePremiereBundle.getEpisodeLocal().getUrl())
                    .episodeNumber(episodePremiereBundle.getEpisodeLocal().getEpisodeNumber())
                    .imageMedium(episodePremiereBundle.getEpisodeLocal().getImageMedium())
                    .imageOriginal(episodePremiereBundle.getEpisodeLocal().getImageOriginal())
                    .name(episodePremiereBundle.getEpisodeLocal().getName())
                    .premiereDate(episodePremiereBundle.getEpisodePremiereLocal().getPremiereDate().toString())
                    .runtime(episodePremiereBundle.getEpisodeLocal().getRuntime())
                    .seasonNumber(episodePremiereBundle.getEpisodeLocal().getSeasonNumber())
                    .summary(episodePremiereBundle.getEpisodeLocal().getSummary())
                    .tvShow(new TvShowDtoObjectMapper().tvShowLocalToDto.apply(episodePremiereBundle.getTvShowLocal()))
                    .build();

    public Function<TvShowPremiereBundle, TvShowPremiereDto> tvShowPremiereBundleToDto = tvShowPremiereBundle ->
            TvShowPremiereDto.builder()
                    .tvShowApiId(tvShowPremiereBundle.getTvShowLocal().getTvShowApiId())
                    .imdbId(tvShowPremiereBundle.getTvShowLocal().getImdbId())
                    .name(tvShowPremiereBundle.getTvShowLocal().getName())
                    .premiere(tvShowPremiereBundle.getTvShowPremiereLocal().getPremiereDate().toString())
                    .officialSite(tvShowPremiereBundle.getTvShowLocal().getOfficialSite())
                    .genre(tvShowPremiereBundle.getTvShowLocal().getGenre())
                    .network(tvShowPremiereBundle.getTvShowLocal().getNetworkName())
                    .summary(tvShowPremiereBundle.getTvShowLocal().getSummary())
                    .status(tvShowPremiereBundle.getTvShowLocal().getStatus())
                    .runtime(tvShowPremiereBundle.getTvShowLocal().getRuntime())
                    .episodeOrder(getEpisodeOrderAfterPremiereDate(tvShowPremiereBundle.getTvShowLocal()))
                    .fullRuntime(tvShowPremiereBundle.getTvShowLocal().getFullRuntime())
                    .imageMedium(tvShowPremiereBundle.getTvShowLocal().getImageMedium())
                    .imageOriginal(tvShowPremiereBundle.getTvShowLocal().getImageOriginal())
                    .build();

    private Integer getEpisodeOrderAfterPremiereDate(TvShowLocal tvShowLocal) {
        return tvShowLocal.getEpisodes().stream()
                .filter(this::isEpisodeAfterPremiereDate)
                .collect(Collectors.toList())
                .size();
    }

    private boolean isEpisodeAfterPremiereDate(EpisodeLocal episodeLocal) {
        return episodeLocal.getPremiereDate() != null &&
                LocalDate.parse(episodeLocal.getPremiereDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).isBefore(LocalDate.now(ZoneId.of("Europe/Warsaw")));
    }

}
