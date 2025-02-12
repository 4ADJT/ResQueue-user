package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.createuser;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.security.AuthTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateUserService {

  private final CreateUserClient createUserClient;
  private final AuthTokenService authTokenService;

  @Value("${keycloak.base-url}")
  private String keycloakBaseUrl;

  public CreateUserService(CreateUserClient createUserClient, AuthTokenService authTokenService) {
    this.createUserClient = createUserClient;
    this.authTokenService = authTokenService;
  }

  public Mono<Void> createUser(CreateUserRequestDTO requestDTO) {
    CreateUserRequest userRequest = CreateUserRequest.fromDTO(requestDTO);
    return authTokenService.getAccessToken()
        .flatMap(token -> createUserClient.createUser(keycloakBaseUrl, token, userRequest));
  }
}
