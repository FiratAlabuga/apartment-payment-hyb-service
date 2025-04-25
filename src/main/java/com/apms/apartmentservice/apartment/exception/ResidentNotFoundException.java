package com.apms.apartmentservice.apartment.exception;

public class ResidentNotFoundException extends RuntimeException {
  public ResidentNotFoundException(String message) {
    super(message);
  }
}
