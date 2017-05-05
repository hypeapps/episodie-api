package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.core.usecase.tvshow.toplist.gettoplist.GetTvShowTopListUseCase;
import pl.hypeapp.episodie.entrypoints.rest.tvshow.gettoplist.GetTvShowTopListEndpoint;
import pl.hypeapp.episodie.entrypoints.rest.tvshow.gettvshow.GetTvShowEndpoint;

@Configuration
public class EndpointConfiguration {

    @Bean
    public GetTvShowEndpoint getTvShowEndpoint(GetTvShowUseCase getTvShowUseCase) {
        return new GetTvShowEndpoint(getTvShowUseCase);
    }

    @Bean
    public GetTvShowTopListEndpoint getTvShowTopListEndpoint(GetTvShowTopListUseCase getTvShowTopListUseCase) {
        return new GetTvShowTopListEndpoint(getTvShowTopListUseCase);
    }

}
