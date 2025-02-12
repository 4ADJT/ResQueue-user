package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.refresh;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login.LoginResponse;
import br.com.imaginer.resqueueuser.domain.exception.InvalidRefreshTokenBadRequestException;
import br.com.imaginer.resqueueuser.domain.exception.InvalidRefreshTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RefreshTokenClient {

  private final WebClient webClient;

  @Value("${keycloak.base-url}")
  private String keycloakBaseUrl;

  @Value("${keycloak.client-id}")
  private String clientId;

  @Value("${keycloak.client-secret}")
  private String clientSecret;

  public RefreshTokenClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }

  public Mono<LoginResponse> refreshAccessToken(String refreshToken) {
    String url = keycloakBaseUrl + "/realms/resqueue/protocol/openid-connect/token";

    return webClient.post()
        .uri(url)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body(BodyInserters.fromFormData("grant_type", "refresh_token")
            .with("client_id", clientId)
            .with("client_secret", clientSecret)
            .with("refresh_token", refreshToken))
        .retrieve()
        .onStatus(status -> status.value() == 403, response ->
            Mono.error(new InvalidRefreshTokenException()))
        .onStatus(status -> status.value() == 400, response ->
            Mono.error(new InvalidRefreshTokenBadRequestException()))
        .bodyToMono(LoginResponse.class);
  }
}
