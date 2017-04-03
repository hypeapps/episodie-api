package pl.hypeapp.episodie.dataproviders.crawler

import spock.lang.Specification

class ImdbTopTvShowsCrawlerDataProviderTest extends Specification {
    private static final String IMDB_TOP_TV_SHOWS_URL = "http://www.imdb.com/chart/toptv"
    ImdbTopTvShowsCrawlerDataProvider imdbTopTvShowsCrawlerDataProvider = new ImdbTopTvShowsCrawlerDataProvider()

    def "shouldGetImdbIds"() {
        given:
        List<String> ids = new LinkedList<>()
        when:
        ids = imdbTopTvShowsCrawlerDataProvider.getIds(IMDB_TOP_TV_SHOWS_URL)
        then:
        ids.size() == 100
    }
}
