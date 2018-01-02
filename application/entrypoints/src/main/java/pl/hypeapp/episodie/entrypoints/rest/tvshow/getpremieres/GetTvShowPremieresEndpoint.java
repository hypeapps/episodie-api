package pl.hypeapp.episodie.entrypoints.rest.tvshow.getpremieres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hypeapp.episodie.core.entity.EpisodePremiereBundle;
import pl.hypeapp.episodie.core.entity.TvShowPremiereBundle;
import pl.hypeapp.episodie.core.usecase.tvshow.ResourceNotFoundException;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetPremieresUseCase;
import pl.hypeapp.episodie.entrypoints.rest.dto.EpisodePremiereDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.PremiereBundleDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.TvShowPremiereDto;
import pl.hypeapp.episodie.entrypoints.rest.dto.mapper.PremiereBundleDtoMapper;
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetTvShowPremieresEndpoint {

    private static final String API_PATH_TV_SHOW_PREMIERES = "api/tvshow/premieres/series";

    private static final String API_GET_EPISODE_PREMIERES = "api/tvshow/premieres/episode";

    private static final String API_GET_ALL_PREMIERES = "api/tvshow/premieres/all";

    private final GetPremieresUseCase getPremieresUseCase;

    public GetTvShowPremieresEndpoint(GetPremieresUseCase getPremieresUseCase) {
        this.getPremieresUseCase = getPremieresUseCase;
    }

    @RequestMapping(value = API_PATH_TV_SHOW_PREMIERES, method = GET)
    public Page<TvShowPremiereDto> getTvShowPremieres(Pageable pageableRequest,
                                                      @RequestParam(value = "fromDate")
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
        Page<TvShowPremiereBundle> tvShowsPremieres;
        try {
            tvShowsPremieres = getPremieresUseCase.getTvShowPremieres(pageableRequest, fromDate);
            return toTvShowPremiereDto(tvShowsPremieres, pageableRequest);
        } catch (ResourceNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = API_GET_EPISODE_PREMIERES, method = GET)
    public Page<EpisodePremiereDto> getEpisodePremieres(Pageable pageableRequest,
                                                        @RequestParam(value = "date")
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Page<EpisodePremiereBundle> episodePremieres;
        try {
            episodePremieres = getPremieresUseCase.getEpisodePremieresLocal(pageableRequest, date);
            return toEpisodePremiereDto(episodePremieres, pageableRequest);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = API_GET_ALL_PREMIERES, method = GET)
    public PremiereBundleDto getAllPremieres(Pageable pageableRequest,
                                             @RequestParam(value = "date")
                                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        PremiereBundleDtoMapper objectMapper = new PremiereBundleDtoMapper();
        try {
            List<TvShowPremiereDto> tvShowPremieresDtos = new ArrayList<>();
            List<TvShowPremiereBundle> tvShowPremiereBundles = getPremieresUseCase.getTvShowPremieresByDate(date);
            if (tvShowPremiereBundles != null && !tvShowPremiereBundles.isEmpty()) {
                tvShowPremieresDtos = tvShowPremiereBundles.stream()
                        .map(objectMapper.tvShowPremiereBundleToDto)
                        .collect(Collectors.toList());
            }
            return new PremiereBundleDto(getEpisodePremieres(pageableRequest, date).getContent(), tvShowPremieresDtos);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    private Page<TvShowPremiereDto> toTvShowPremiereDto(Page<TvShowPremiereBundle> tvShowsPremieres,
                                                        Pageable pageableRequest) {
        PremiereBundleDtoMapper objectMapper = new PremiereBundleDtoMapper();
        List<TvShowPremiereDto> tvShowDtos = tvShowsPremieres.getContent().stream()
                .map(objectMapper.tvShowPremiereBundleToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(tvShowDtos, pageableRequest, tvShowsPremieres.getTotalElements());
    }

    private Page<EpisodePremiereDto> toEpisodePremiereDto(Page<EpisodePremiereBundle> episodePremieres,
                                                          Pageable pageableRequest) {
        PremiereBundleDtoMapper mapper = new PremiereBundleDtoMapper();
        List<EpisodePremiereDto> dtos = episodePremieres.getContent().stream()
                .map(mapper.episodePremiereBundleToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageableRequest, episodePremieres.getTotalElements());
    }

}
