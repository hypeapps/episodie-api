package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.core.usecase.GetEpisode;
import pl.hypeapp.core.usecase.GetEpisodeUseCase;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetEpisodeUseCase getEpisodeUseCase(GetEpisode getEpisode){
        return new GetEpisodeUseCase(getEpisode);
    }
}
