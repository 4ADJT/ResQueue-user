package br.com.imaginer.resqueueuser.domain.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException() {
    super("Credenciais inválidas. Verifique o email e a senha.");
  }
}
