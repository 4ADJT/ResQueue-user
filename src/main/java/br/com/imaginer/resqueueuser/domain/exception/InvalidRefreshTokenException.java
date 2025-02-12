package br.com.imaginer.resqueueuser.domain.exception;

public class InvalidRefreshTokenException extends RuntimeException {
  public InvalidRefreshTokenException() {
    super("Refresh token inválido ou expirado.");
  }
}
