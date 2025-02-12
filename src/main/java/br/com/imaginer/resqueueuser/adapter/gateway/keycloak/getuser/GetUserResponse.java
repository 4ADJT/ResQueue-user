package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record GetUserResponse(
    String id,
    String email,
    String firstName,
    String lastName,
    boolean emailVerified,
    boolean enabled,
    LocalDateTime createdAt
) {
  public static GetUserResponse fromKeycloakResponse(KeycloakUser keycloakUser) {
    return new GetUserResponse(
        keycloakUser.id(),
        keycloakUser.email(),
        keycloakUser.firstName(),
        keycloakUser.lastName(),
        keycloakUser.emailVerified(),
        keycloakUser.enabled(),
        Instant.ofEpochMilli(keycloakUser.createdTimestamp())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    );
  }
}
