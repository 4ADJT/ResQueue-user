package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoginService {

  private final LoginClient loginClient;

  public LoginService(LoginClient loginClient) {
    this.loginClient = loginClient;
  }

  public Mono<LoginResponse> login(LoginRequest request) {
    return loginClient.login(request);
  }
}
