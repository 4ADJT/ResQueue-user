package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login;

import br.com.imaginer.resqueueuser.domain.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class LoginClient {

  private final WebClient webClient;

  @Value("${keycloak.base-url}")
  private String keycloakBaseUrl;

  @Value("${keycloak.client-id}")
  private String clientId;

  @Value("${keycloak.client-secret}")
  private String clientSecret;

  public LoginClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }

  public Mono<LoginResponse> login(LoginRequest loginRequest) {
    String url = keycloakBaseUrl + "/realms/resqueue/protocol/openid-connect/token";

    return webClient.post()
        .uri(url)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body(BodyInserters.fromFormData("grant_type", "password")
            .with("client_id", clientId)
            .with("client_secret", clientSecret)
            .with("username", loginRequest.username())
            .with("password", loginRequest.password()))
        .retrieve()
        .onStatus(status -> status.value() == 401, response -> Mono.error(new UnauthorizedException()))
        .bodyToMono(LoginResponse.class);
  }
}
