package br.com.imaginer.resqueueuser.domain.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String email) {
    super("Usuário com email '" + email + "' não encontrado no Keycloak.");
  }
}
