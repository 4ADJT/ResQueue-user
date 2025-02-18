package br.com.imaginer.resqueueuser.adapter.controller;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser.GetUserResponse;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser.GetUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
  public Mono<ResponseEntity<GetUserResponse>> getUser(@RequestParam String email) {
    return getUserService.getUser(email)
        .map(ResponseEntity::ok);
  }
}
