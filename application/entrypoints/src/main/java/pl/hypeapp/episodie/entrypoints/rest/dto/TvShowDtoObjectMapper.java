package pl.hypeapp.episodie.entrypoints.rest.dto;

import pl.hypeapp.core.entity.database.EpisodeLocal;
import pl.hypeapp.core.entity.database.SeasonLocal;
import pl.hypeapp.core.entity.database.TvShowLocal;

import java.util.function.Function;
import java.util.stream.Collectors;

public class TvShowDtoObjectMapper {

    public Function<TvShowLocal, TvShowDto> tvShowLocalToDto = new Function<TvShowLocal, TvShowDto>() {
        @Override
        public TvShowDto apply(TvShowLocal tvShowLocal) {
            return TvShowDto.builder()
                    .tvShowApiId(tvShowLocal.getTvShowApiId())
                    .imdbId(tvShowLocal.getImdbId())
                    .name(tvShowLocal.getName())
                    .status(tvShowLocal.getStatus())
                    .runtime(tvShowLocal.getRuntime())
                    .fullRuntime(tvShowLocal.getFullRuntime())
                    .premiered(tvShowLocal.getPremiered())
                    .summary(tvShowLocal.getSummary())
                    .imageOriginal(tvShowLocal.getImageOriginal())
                    .imageMedium(tvShowLocal.getImageMedium())
                    .episodes(tvShowLocal.getEpisodes().stream()
                            .map(episodeLocal -> episodeLocalToDto.apply(episodeLocal))
                            .collect(Collectors.toList()))
                    .seasons(tvShowLocal.getSeasons().stream()
                            .map(seasonLocal -> seasonLocalToDto.apply(seasonLocal))
                            .collect(Collectors.toList()))
                    .build();
        }
    };

    public Function<EpisodeLocal, EpisodeDto> episodeLocalToDto = episodeLocal -> EpisodeDto.builder()
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

    public Function<SeasonLocal, SeasonDto> seasonLocalToDto = seasonLocal -> SeasonDto.builder()
            .seasonApiId(seasonLocal.getSeasonApiId())
            .url(seasonLocal.getUrl())
            .seasonNumber(seasonLocal.getSeasonNumber())
            .episodeOrder(seasonLocal.getEpisodeOrder())
            .runtime(seasonLocal.getRuntime())
            .premiereDate(seasonLocal.getPremiereDate())
            .endDate(seasonLocal.getEndDate())
            .summary(seasonLocal.getSummary())
            .build();

}
