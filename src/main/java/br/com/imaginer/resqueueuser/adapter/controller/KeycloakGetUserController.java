package br.com.imaginer.resqueueuser.adapter.controller;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser.GetUserResponse;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser.GetUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Tag(name = "Keycloak User Details")
@RestController
@RequestMapping("/users")
public class KeycloakGetUserController {

  private final GetUserService getUserService;

  public KeycloakGetUserController(GetUserService getUserService) {
    this.getUserService = getUserService;
  }

  @Operation(description = "Get user details by email.", security = { @SecurityRequirement(name = "bearer-key") })
  @GetMapping
  public Mono<ResponseEntity<GetUserResponse>> getUser(
      @RequestParam String email,
      @AuthenticationPrincipal Jwt jwt
  ) {

    UUID userId = UUID.fromString(jwt.getSubject());
    String userEmailFromToken = jwt.getClaim("email");

    log.info("Usuário autenticado: " + userId + " - " + userEmailFromToken);

    return getUserService.getUser(email)
        .map(ResponseEntity::ok);
  }
}
