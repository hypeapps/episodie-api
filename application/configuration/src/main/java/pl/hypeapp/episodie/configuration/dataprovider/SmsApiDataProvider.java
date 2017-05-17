package pl.hypeapp.episodie.configuration.dataprovider;

import com.twilio.sdk.TwilioRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.dataproviders.sms.FailedJobSmsDataProvider;

@Configuration
public class SmsApiDataProvider {
    //TO USE API YOU NEED TWILIO ACCOUNT SID AND AUTH TOKEN
    private static final String ACCOUNT_SID = "YOU NEED YOUR ACCOUNT SID";

    private static final String AUTH_TOKEN = "YOU NEED YOUR AUTH TOKEN";

    @Bean
    public TwilioRestClient twilioRestClient() {
        return new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Bean
    public FailedJobSmsDataProvider failedJobSmsDataProvider(TwilioRestClient twilioRestClient) {
        return new FailedJobSmsDataProvider(twilioRestClient);
    }

}
