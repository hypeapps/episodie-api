package pl.hypeapp.episodie.dataproviders.api.tvmaze.schedule;

import org.springframework.web.client.RestTemplate;
import pl.hypeapp.episodie.core.entity.api.tvmaze.EpisodePremiereRemote;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetEpisodePremieresRemote;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EpisodePremiereTvMazeApiDataProviderRemote implements GetEpisodePremieresRemote {

    private final RestTemplate restTemplate;

    public EpisodePremiereTvMazeApiDataProviderRemote(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<EpisodePremiereRemote> getEpisodePremieres(Date date) {
        String byDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return Arrays.asList(restTemplate.getForObject("http://api.tvmaze.com/schedule?country=US&date=" + byDate, EpisodePremiereRemote[].class));
    }

}
