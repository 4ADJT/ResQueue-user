package br.com.imaginer.resqueueuser.adapter.gateway.keycloak.createuser;

import java.util.List;

public record CreateUserRequest(
    boolean enabled,
    String email,
    String firstName,
    String lastName,
    List<Credential> credentials
) {
  public record Credential(String type, String value, boolean temporary) {}

  public static CreateUserRequest fromDTO(CreateUserRequestDTO dto) {
    return new CreateUserRequest(
        true,
        dto.email(),
        dto.firstName(),
        dto.lastName(),
        List.of(new Credential("password", dto.password(), false))
    );
  }
}
