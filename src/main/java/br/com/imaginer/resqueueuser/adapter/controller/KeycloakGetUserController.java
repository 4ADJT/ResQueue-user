package br.com.imaginer.resqueueuser.adapter.controller;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser.GetUserResponse;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser.GetUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class KeycloakGetUserController {

  private final GetUserService getUserService;

  public KeycloakGetUserController(GetUserService getUserService) {
    this.getUserService = getUserService;
  }

  @GetMapping
  public Mono<ResponseEntity<GetUserResponse>> getUser(@RequestParam String email) {
    return getUserService.getUser(email)
        .map(ResponseEntity::ok);
  }
}
