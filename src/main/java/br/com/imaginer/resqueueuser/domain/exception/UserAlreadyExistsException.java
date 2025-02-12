package br.com.imaginer.resqueueuser.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String email) {
    super("O usuário com email '" + email + "' já existe no Keycloak.");
  }
}
