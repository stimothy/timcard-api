package com.steventimothy.timcard.schemas.exceptions;

/**
 * <h1>The UnauthorizedException Class</h1>
 * <p>This class is the exception when the request is made by an unknown
 * Identity.</p>
 */
public class UnauthorizedException extends RuntimeException {

  /**
   * The constructor
   * @param message The message of why this exception was thrown.
   */
  public UnauthorizedException(String message) {
    super(message);
  }
}
