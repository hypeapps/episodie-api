package pl.hypeapp.episodie.entrypoints.rest.tvshow.getpremieres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.episodie.core.entity.TvShowPremiereBundle;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetPremieresUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowDtoObjectMapper;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowPremiereDto;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetTvShowPremieresEndpoint {

    private static final String API_PATH = "api/tvshow/premieres";

    private final GetPremieresUseCase getPremieresUseCase;

    public GetTvShowPremieresEndpoint(GetPremieresUseCase getPremieresUseCase) {
        this.getPremieresUseCase = getPremieresUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public Page<TvShowPremiereDto> getPremieres(Pageable pageableRequest) {
        Page<TvShowPremiereBundle> tvShowsPremieres;
        try {
            tvShowsPremieres = getPremieresUseCase.getPremieres(pageableRequest);
            return toDto(tvShowsPremieres, pageableRequest);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    private Page<TvShowPremiereDto> toDto(Page<TvShowPremiereBundle> tvShowsPremieres, Pageable pageableRequest) {
        TvShowDtoObjectMapper objectMapper = new TvShowDtoObjectMapper();
        List<TvShowPremiereDto> tvShowDtos = tvShowsPremieres.getContent().stream()
                .map(objectMapper.tvShowPremiereBundleToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(tvShowDtos, pageableRequest, tvShowsPremieres.getTotalElements());
    }

}
