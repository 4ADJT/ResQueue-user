package br.com.imaginer.resqueueuser.adapter.controller;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login.LoginRequest;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login.LoginResponse;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login.LoginService;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.refresh.RefreshTokenRequest;
import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.refresh.RefreshTokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final LoginService loginService;
  private final RefreshTokenService refreshTokenService;

  public AuthController(LoginService loginService, RefreshTokenService refreshTokenService) {
    this.loginService = loginService;
    this.refreshTokenService = refreshTokenService;
  }

  @PostMapping("/login")
  public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest request) {
    return loginService.login(request)
        .map(ResponseEntity::ok);
  }

  @PostMapping("/login/refresh")
  public Mono<ResponseEntity<LoginResponse>> refreshAccessToken(@RequestBody RefreshTokenRequest request) {
    return refreshTokenService.refreshToken(request.refreshToken())
        .map(ResponseEntity::ok);
  }
}
