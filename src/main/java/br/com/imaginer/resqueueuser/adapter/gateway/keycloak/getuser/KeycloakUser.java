package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.getuser;

public record KeycloakUser(
    String id,
    String email,
    String firstName,
    String lastName,
    boolean emailVerified,
    boolean enabled,
    long createdTimestamp
) {}
