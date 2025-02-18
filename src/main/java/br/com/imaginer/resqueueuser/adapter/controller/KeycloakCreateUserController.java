package br.com.imaginer.resqueueuser.adapter.controller;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.createuser.CreateUserRequestDTO;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.createuser.CreateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Create User")
@RestController
@RequestMapping("/users")
public class KeycloakCreateUserController {

  private final CreateUserService createUserService;

  public KeycloakCreateUserController(CreateUserService createUserService) {
    this.createUserService = createUserService;
  }

  @Operation(description = "Create new user.")
  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> createUser(@RequestBody CreateUserRequestDTO request) {
    return createUserService.createUser(request);
  }

}
