package pl.hypeapp.episodie.dataproviders.api.tvmaze.update;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvMazeId;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;
import pl.hypeapp.episodie.dataproviders.api.tvmaze.tvshow.TvShowTvMazeApiDataProvider;

import javax.xml.ws.http.HTTPException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TvShowTvMazeApiDataProviderTest {

    private Retryer<TvShowRemote> remoteRetryer = RetryerBuilder.<TvShowRemote>newBuilder()
            .retryIfExceptionOfType(HTTPException.class)
            .retryIfRuntimeException()
            .withWaitStrategy(WaitStrategies.fixedWait(6, TimeUnit.SECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();

    private RestTemplate restTemplate = new RestTemplate();

    private TvShowTvMazeApiDataProvider tvShowTvMazeApiDataProvider = new TvShowTvMazeApiDataProvider(restTemplate, remoteRetryer);

    @Test
    public void shouldGetTvShow() {
        Optional<TvShowRemote> tvShowByMazeId = tvShowTvMazeApiDataProvider.getTvShowByMazeId(new TvMazeId("545"));
        assertThat(tvShowByMazeId).isPresent();
    }

}
