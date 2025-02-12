package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AuthTokenService {

  private final AuthTokenClient authTokenClient;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String authUrl;

  @Value("${keycloak.client-id}")
  private String clientId;

  @Value("${keycloak.client-secret}")
  private String clientSecret;

  private final AtomicReference<AuthTokenResponse> cachedToken = new AtomicReference<>();
  private Instant tokenExpiration = Instant.MIN;

  public AuthTokenService(AuthTokenClient authTokenClient) {
    this.authTokenClient = authTokenClient;
  }

  public Mono<String> getAccessToken() {
    if (Instant.now().isBefore(tokenExpiration)) {
      return Mono.just(cachedToken.get().accessToken());
    }
    return authTokenClient.getToken(authUrl, clientId, clientSecret)
        .doOnNext(token -> {
          cachedToken.set(token);
          tokenExpiration = Instant.now().plusSeconds(token.expiresIn() - 10);
        })
        .map(AuthTokenResponse::accessToken);
  }
}
