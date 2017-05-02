package pl.hypeapp.episodie.dataproviders.api.tvmaze.update

import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class TvShowsUpdatesApiDataProviderTest extends Specification {

    def "shouldCallForUpdates"() {
        given:
        RestTemplate restTemplate = new RestTemplate()
        when:
        Map<String, Integer> s = (Map<String, Integer>) restTemplate.getForObject("http://api.tvmaze.com/updates/shows", Map.class)
        then:
        1 == 1
        println(s.get("2"))
    }

}
