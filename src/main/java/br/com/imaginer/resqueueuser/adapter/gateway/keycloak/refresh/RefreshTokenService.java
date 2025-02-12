package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.refresh;

import br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login.LoginResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RefreshTokenService {

  private final RefreshTokenClient refreshTokenClient;

  public RefreshTokenService(RefreshTokenClient refreshTokenClient) {
    this.refreshTokenClient = refreshTokenClient;
  }

  public Mono<LoginResponse> refreshToken(String refreshToken) {
    return refreshTokenClient.refreshAccessToken(refreshToken);
  }
}
