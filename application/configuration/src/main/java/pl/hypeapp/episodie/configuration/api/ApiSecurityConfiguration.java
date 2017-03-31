package pl.hypeapp.episodie.configuration.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String REALM = "API_REALM";
    private static final String USER_ROLE = "USER";
    private final ApiCredentialsConfiguration apiCredentials;

    @Autowired
    public ApiSecurityConfiguration(ApiCredentialsConfiguration apiCredentials) {
        this.apiCredentials = apiCredentials;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(apiCredentials.getApiUsername()).password(apiCredentials.getApiPassword())
                .roles(USER_ROLE);
    }

    @Bean
    public UnauthorizedAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new UnauthorizedAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("api/**").hasRole(USER_ROLE)
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
