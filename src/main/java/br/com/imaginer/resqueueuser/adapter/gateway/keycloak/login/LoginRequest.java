package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.login;

public record LoginRequest(
    String username,
    String password
) {}
