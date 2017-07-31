package pl.hypeapp.episodie.entrypoints.rest.dto;

import pl.hypeapp.episodie.core.entity.database.TvShowLocal;

import java.util.function.Function;

public class SearchNameResultDtoMapper {

    public Function<TvShowLocal, SearchNameResultDto> tvShowLocalToSearchNameDto = tvShowLocal -> SearchNameResultDto.builder()
            .tvShowApiId(tvShowLocal.getTvShowApiId())
            .name(tvShowLocal.getName())
            .fullRuntime(tvShowLocal.getFullRuntime())
            .build();

}
