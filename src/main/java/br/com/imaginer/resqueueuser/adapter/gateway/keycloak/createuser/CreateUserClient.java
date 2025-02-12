package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.createuser;

import br.com.imaginer.resqueueuser.domain.exception.UserAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CreateUserClient {

  private final WebClient webClient;

  public CreateUserClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }

  public Mono<Void> createUser(String baseUrl, String token, CreateUserRequest userRequest) {
    String url = baseUrl + "/admin/realms/resqueue/users";

    return webClient.post()
        .uri(url)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .bodyValue(userRequest)
        .retrieve()
        .onStatus(HttpStatus.CONFLICT::equals, response ->
            Mono.error(new UserAlreadyExistsException(userRequest.email()))
        )
        .toBodilessEntity()
        .then();
  }
}
