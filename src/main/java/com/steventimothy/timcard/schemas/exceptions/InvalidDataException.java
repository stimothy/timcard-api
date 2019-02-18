package com.steventimothy.timcard.schemas.exceptions;

/**
 * <h1>The InvalidDataException Class</h1>
 * <p>This class is an exception class for requests that do not have
 * correct data.</p>
 */
public class InvalidDataException extends RuntimeException {

  /**
   * The constructor.
   *
   * @param message The message as why this exception was thrown.
   */
  public InvalidDataException(String message) {
    super(message);
  }

  /**
   * The constructor.
   *
   * @param message   The message as why this exception was thrown.
   * @param throwable The throwable that caused this exception.
   */
  public InvalidDataException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
