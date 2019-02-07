package com.steventimothy.timcard.schemas.exceptions;

/**
 * <h1>The ForbiddenException Class</h1>
 * <p>This class is an exception class for requests that do not have
 * The correct permission.</p>
 */
public class ForbiddenException extends RuntimeException {

  /**
   * The constructor.
   * @param message The message as why this exception was thrown.
   */
  public ForbiddenException(String message) {
    super(message);
  }
}
