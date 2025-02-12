package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser;

import br.com.imaginer.resqueueuser.domain.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class GetUserClient {

  private final WebClient webClient;

  public GetUserClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }

  public Mono<GetUserResponse> getUser(String baseUrl, String token, String email) {
    String url = baseUrl + "/admin/realms/resqueue/users?username=" + email;

    return webClient.get()
        .uri(url)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .retrieve()
        .bodyToMono(KeycloakUser[].class)
        .map(Arrays::asList)
        .flatMap(users -> {
          if (users.isEmpty()) {
            return Mono.error(new UserNotFoundException(email));
          }
          return Mono.just(GetUserResponse.fromKeycloakResponse(users.getFirst()));
        });
  }
}
