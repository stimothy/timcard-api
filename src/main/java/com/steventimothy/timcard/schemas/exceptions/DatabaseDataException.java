package com.steventimothy.timcard.schemas.exceptions;

/**
 * <h1>The DatabaseDataException Class</h1>
 * <p>This class is an exception class for database exceptions when
 * querying the database..</p>
 */
public class DatabaseDataException extends RuntimeException {

  /**
   * The constructor.
   *
   * @param message The message as why this exception was thrown.
   */
  public DatabaseDataException(String message) {
    super(message);
  }

  /**
   * The constructor.
   *
   * @param message   The message as why this exception was thrown.
   * @param throwable The throwable that caused this exception.
   */
  public DatabaseDataException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
