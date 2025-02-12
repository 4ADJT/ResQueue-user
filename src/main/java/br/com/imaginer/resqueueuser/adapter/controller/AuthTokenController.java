package br.com.imaginer.resqueueuser.adapter.controller;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.security.AuthTokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthTokenController {

  private final AuthTokenService authTokenService;

  public AuthTokenController(AuthTokenService authTokenService) {
    this.authTokenService = authTokenService;
  }

  @GetMapping("/token")
  public Mono<String> getAuthToken() {
    return authTokenService.getAccessToken()
        .doOnNext(token -> System.out.println("Token JWT: " + token));
  }
}
