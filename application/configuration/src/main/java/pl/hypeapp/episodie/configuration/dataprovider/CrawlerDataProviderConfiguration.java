package pl.hypeapp.episodie.configuration.dataprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.dataproviders.crawler.ImdbMostPopularTvShowsCrawlerDataProvider;
import pl.hypeapp.episodie.dataproviders.crawler.ImdbTopTvShowsCrawlerDataProvider;

@Configuration
public class CrawlerDataProviderConfiguration {

    @Bean
    public ImdbTopTvShowsCrawlerDataProvider imdbTopTvShowsCrawlerDataProvider() {
        return new ImdbTopTvShowsCrawlerDataProvider();
    }

    @Bean
    public ImdbMostPopularTvShowsCrawlerDataProvider imdbMostPopularTvShowsCrawlerDataProvider() {
        return new ImdbMostPopularTvShowsCrawlerDataProvider();
    }

}
