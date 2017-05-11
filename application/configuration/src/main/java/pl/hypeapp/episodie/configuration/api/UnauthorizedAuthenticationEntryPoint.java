package pl.hypeapp.episodie.configuration.api;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UnauthorizedAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final String REALM = "API_REALM";

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

        StringBuilder stringBuilder = new StringBuilder();
        PrintWriter writer = response.getWriter();
        writer.println(stringBuilder
                .append("HTTP Status ")
                .append(response.getStatus())
                .append(" : ")
                .append(authException.getMessage()).toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName(REALM);
        super.afterPropertiesSet();
    }

}
