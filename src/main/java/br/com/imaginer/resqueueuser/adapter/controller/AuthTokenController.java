package br.com.imaginer.resqueueuser.adapter.controller;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.security.AuthTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthTokenController {

  private final AuthTokenService authTokenService;

  public AuthTokenController(AuthTokenService authTokenService) {
    this.authTokenService = authTokenService;
  }

  @Operation(hidden = true)
  @GetMapping("/token")
  public Mono<String> getAuthToken() {
    return authTokenService.getAccessToken()
        .doOnNext(token -> log.info("Token JWT: " + token));
  }
}
