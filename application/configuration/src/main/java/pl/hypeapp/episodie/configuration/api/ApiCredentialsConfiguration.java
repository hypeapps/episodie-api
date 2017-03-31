package pl.hypeapp.episodie.configuration.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiCredentialsConfiguration {

    @Bean
    public String getApiUsername() {
        return "YOUR_API_USERNAME";
    }

    @Bean
    public String getApiPassword() {
        return "YOUR_API_PASSWORD";
    }

}
