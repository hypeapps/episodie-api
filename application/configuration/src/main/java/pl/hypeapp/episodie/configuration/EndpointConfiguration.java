package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.core.usecase.GetEpisodeUseCase;
import pl.hypeapp.episodie.entrypoints.rest.episode.GetEpisodeEndpoint;

@Configuration
public class EndpointConfiguration {

    @Bean
    public GetEpisodeEndpoint getEpisodeEndpoint(GetEpisodeUseCase getEpisodeUseCase){
        return new GetEpisodeEndpoint(getEpisodeUseCase);
    }
}
