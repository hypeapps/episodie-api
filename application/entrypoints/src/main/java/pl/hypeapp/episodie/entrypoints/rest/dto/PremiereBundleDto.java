package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class PremiereBundleDto {

    List<EpisodePremiereDto> episodePremiereDtos;

    List<TvShowPremiereDto> tvShowPremiereDtos;

}
