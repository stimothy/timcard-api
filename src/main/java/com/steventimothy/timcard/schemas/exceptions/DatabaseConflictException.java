package com.steventimothy.timcard.schemas.exceptions;

/**
 * <h1>The DatabaseConflictException Class</h1>
 * <p>This class is an exception class for requests that create a
 * conflict in the database.</p>
 */
public class DatabaseConflictException extends RuntimeException {

  /**
   * The constructor.
   * @param message A message as to why the exception was thrown.
   */
  public DatabaseConflictException(String message) {
    super(message);
  }
}
