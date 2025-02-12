package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthTokenResponse(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("expires_in") Long expiresIn,
    @JsonProperty("refresh_expires_in") Long refreshExpiresIn,
    @JsonProperty("token_type") String tokenType,
    @JsonProperty("not-before-policy") int notBeforePolicy,
    @JsonProperty("scope") String scope
) {}
