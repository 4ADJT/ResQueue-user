package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.createuser;

public record CreateUserRequestDTO(
    String email,
    String firstName,
    String lastName,
    String password
) {}
