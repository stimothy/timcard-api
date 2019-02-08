package com.steventimothy.timcard.schemas.exceptions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseConflictExceptionTest extends ExceptionsBaseComponent {

  /**
   * Tests that the exception can be thrown.
   */
  @Test(expected = DatabaseConflictException.class)
  public void testDatabaseConflictException_Thrown() {
    throw new DatabaseConflictException("There was a conflict.");
  }

  /**
   * Tests that the exception can be caught.
   */
  @Test
  public void testDatabaseConflictException_Caught() {
    try {
      throw new DatabaseConflictException("There was a conflict.");
    }
    catch (DatabaseConflictException ex) {
      assertThat(ex.getMessage())
          .isEqualTo("There was a conflict.");
    }
  }
}
