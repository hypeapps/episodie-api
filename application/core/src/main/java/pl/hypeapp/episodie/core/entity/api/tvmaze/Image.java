package pl.hypeapp.episodie.core.entity.api.tvmaze;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class Image {

    private String medium;

    private String original;
}