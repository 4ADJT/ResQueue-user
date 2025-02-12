package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.security.AuthTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetUserService {

  private final GetUserClient getUserClient;
  private final AuthTokenService authTokenService;

  @Value("${keycloak.base-url}")
  private String keycloakBaseUrl;

  public GetUserService(GetUserClient getUserClient, AuthTokenService authTokenService) {
    this.getUserClient = getUserClient;
    this.authTokenService = authTokenService;
  }

  public Mono<GetUserResponse> getUser(String email) {
    return authTokenService.getAccessToken()
        .flatMap(token -> getUserClient.getUser(keycloakBaseUrl, token, email));
  }
}
