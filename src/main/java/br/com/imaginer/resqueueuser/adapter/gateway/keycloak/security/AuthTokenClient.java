package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.security;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthTokenClient {

  private final WebClient webClient;

  public AuthTokenClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }

  public Mono<AuthTokenResponse> getToken(String authUrl, String clientId, String clientSecret) {
    return webClient.post()
        .uri(authUrl + "/protocol/openid-connect/token")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(BodyInserters.fromFormData("grant_type", "client_credentials")
            .with("client_id", clientId)
            .with("client_secret", clientSecret))
        .retrieve()
        .bodyToMono(AuthTokenResponse.class);
  }
}
