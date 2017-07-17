package pl.hypeapp.episodie.core.entity.crawler;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ImdbPremiere {

    private final String imdbId;

    private final LocalDate premiereDate;

}
