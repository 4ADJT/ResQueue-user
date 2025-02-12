package br.com.imaginer.resqueueuser.domain.exception;

public class InvalidRefreshTokenBadRequestException extends RuntimeException {
  public InvalidRefreshTokenBadRequestException() {
    super("Refresh token inválido ou malformado.");
  }
}
