package com.apms.apartmentservice.auth.exception;

public class PasswordNotValidException extends RuntimeException {
  public PasswordNotValidException(String message) {
    super(message);
  }
}
