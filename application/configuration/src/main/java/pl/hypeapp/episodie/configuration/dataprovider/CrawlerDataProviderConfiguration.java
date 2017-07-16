package pl.hypeapp.episodie.configuration.dataprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.dataproviders.crawler.ImdbMostPopularCrawlerDataProvider;
import pl.hypeapp.episodie.dataproviders.crawler.ImdbPremieresCrawlerDataProvider;
import pl.hypeapp.episodie.dataproviders.crawler.ImdbTopListCrawlerDataProvider;

@Configuration
public class CrawlerDataProviderConfiguration {

    @Bean
    public ImdbTopListCrawlerDataProvider imdbTopListCrawlerDataProvider() {
        return new ImdbTopListCrawlerDataProvider();
    }

    @Bean
    public ImdbMostPopularCrawlerDataProvider imdbMostPopularCrawlerDataProvider() {
        return new ImdbMostPopularCrawlerDataProvider();
    }

    @Bean
    public ImdbPremieresCrawlerDataProvider imdbPremieresCrawlerDataProvider() {
        return new ImdbPremieresCrawlerDataProvider();
    }

}
