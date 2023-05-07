package br.com.unipac.divan.divanapi.security.interceptions;

import br.com.unipac.divan.divanapi.api.dto.request.authentication.AuthenticationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.AuthenticationResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * The type Auth interceptor.
 */
public class AuthInterceptor implements ClientHttpRequestInterceptor {
    private String email;
    private String password;

    /**
     * Instantiates a new Auth interceptor.
     *
     * @param email    the email
     * @param password the password
     */
    public AuthInterceptor(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, encodeJWTAuth(email, password));

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    /**
     * Encode jwt auth string.
     *
     * @param username the username
     * @param password the password
     * @return the string
     */
    public static String encodeJWTAuth(String username, String password) {
        AuthenticationRequest login = new AuthenticationRequest();

        login.setEmail(username);
        login.setPassword(password);

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");

        HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(login, headers);

        String url = "http://localhost:8181/login";
        AuthenticationResponse response = restTemplate.postForObject(url, requestEntity, AuthenticationResponse.class);
        return response.getAccessToken();
    }
}
