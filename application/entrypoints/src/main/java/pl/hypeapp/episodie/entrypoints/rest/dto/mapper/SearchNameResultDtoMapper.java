package pl.hypeapp.episodie.entrypoints.rest.dto.mapper;

import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.entrypoints.rest.dto.SearchNameResultDto;

import java.util.function.Function;

public class SearchNameResultDtoMapper {

    private TvShowDtoObjectMapper tvShowDtoObjectMapper = new TvShowDtoObjectMapper();

    public Function<TvShowLocal, SearchNameResultDto> tvShowLocalToSearchNameDto = tvShowLocal -> SearchNameResultDto.builder()
            .tvShowApiId(tvShowLocal.getTvShowApiId())
            .name(tvShowLocal.getName())
            .episodeOrder(tvShowDtoObjectMapper.getEpisodeOrderAfterPremiereDate(tvShowLocal))
            .fullRuntime(tvShowDtoObjectMapper.calculateActualFullRuntime(tvShowLocal))
            .imageMedium(tvShowLocal.getImageMedium())
            .imageOriginal(tvShowLocal.getImageOriginal())
            .build();

}
