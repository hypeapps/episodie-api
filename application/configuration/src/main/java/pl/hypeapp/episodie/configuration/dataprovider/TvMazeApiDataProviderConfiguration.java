package pl.hypeapp.episodie.configuration.dataprovider;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowsUpdatesRemote;
import pl.hypeapp.episodie.dataproviders.api.tvmaze.id.TvShowIdTvMazeApiDataProvider;
import pl.hypeapp.episodie.dataproviders.api.tvmaze.search.TvShowSearchTvMazeApiDataProvider;
import pl.hypeapp.episodie.dataproviders.api.tvmaze.tvshow.TvShowTvMazeApiDataProvider;
import pl.hypeapp.episodie.dataproviders.api.tvmaze.update.TvShowsUpdatesApiDataProvider;

import javax.xml.ws.http.HTTPException;
import java.util.concurrent.TimeUnit;

@Configuration
public class TvMazeApiDataProviderConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Retryer retryer() {
        return RetryerBuilder.newBuilder()
                .retryIfExceptionOfType(HTTPException.class)
                .retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(1))
                .build();
    }

    @Bean
    public TvShowTvMazeApiDataProvider tvShowTvMazeApiDataProvider(RestTemplate restTemplate, Retryer<TvShowRemote> retryer) {
        return new TvShowTvMazeApiDataProvider(restTemplate, retryer);
    }

    @Bean
    public TvShowIdTvMazeApiDataProvider tvShowIdTvMazeApiDataProvider(RestTemplate restTemplate, Retryer<TvMazeId> retryer) {
        return new TvShowIdTvMazeApiDataProvider(restTemplate, retryer);
    }

    @Bean
    public TvShowsUpdatesApiDataProvider tvShowsUpdatesApiDataProvider(RestTemplate restTemplate, Retryer<TvShowsUpdatesRemote> retryer) {
        return new TvShowsUpdatesApiDataProvider(restTemplate, retryer);
    }

    @Bean
    public TvShowSearchTvMazeApiDataProvider tvShowSearchTvMazeApiDataProvider(RestTemplate restTemplate) {
        return new TvShowSearchTvMazeApiDataProvider(restTemplate);
    }

}
