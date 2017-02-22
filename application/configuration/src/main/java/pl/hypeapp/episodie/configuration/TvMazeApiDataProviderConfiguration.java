package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.episodie.dataproviders.network.api.tvmaze.TvMazeApiDataProvider;

@Configuration
public class TvMazeApiDataProviderConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public TvMazeApiDataProvider tvMazeApiDataProvider(RestTemplate restTemplate){
        return new TvMazeApiDataProvider(restTemplate);
    }


}
