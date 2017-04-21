package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.episodie.entrypoints.rest.tvshow.GetTvShowEndpoint;

@Configuration
public class EndpointConfiguration {

    @Bean
    public GetTvShowEndpoint episodeEndpoint(GetTvShowUseCase getTvShowUseCase) {
        return new GetTvShowEndpoint(getTvShowUseCase);
    }

}
