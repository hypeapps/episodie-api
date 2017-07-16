package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.search.SearchTvShowUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.gettoplist.GetTopListUseCase;
import pl.hypeapp.episodie.entrypoints.rest.tvshow.getmostpopular.GetMostPopularTvShowsEndpoint;
import pl.hypeapp.episodie.entrypoints.rest.tvshow.gettoplist.GetTvShowTopListEndpoint;
import pl.hypeapp.episodie.entrypoints.rest.tvshow.gettvshow.GetTvShowEndpoint;
import pl.hypeapp.episodie.entrypoints.rest.tvshow.search.SearchTvShowEndpoint;

@Configuration
public class EndpointConfiguration {

    @Bean
    public GetTvShowEndpoint getTvShowEndpoint(GetTvShowUseCase getTvShowUseCase) {
        return new GetTvShowEndpoint(getTvShowUseCase);
    }

    @Bean
    public GetTvShowTopListEndpoint getTvShowTopListEndpoint(GetTopListUseCase getTopListUseCase) {
        return new GetTvShowTopListEndpoint(getTopListUseCase);
    }

    @Bean
    public GetMostPopularTvShowsEndpoint getMostPopularTvShowsEndpoint(GetMostPopularUseCase getMostPopularUseCase) {
        return new GetMostPopularTvShowsEndpoint(getMostPopularUseCase);
    }

    @Bean
    public SearchTvShowEndpoint searchTvShowEndpoint(SearchTvShowUseCase searchTvShowUseCase) {
        return new SearchTvShowEndpoint(searchTvShowUseCase);
    }

}
