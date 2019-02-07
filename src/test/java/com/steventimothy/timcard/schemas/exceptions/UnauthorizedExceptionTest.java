package com.steventimothy.timcard.schemas.exceptions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnauthorizedExceptionTest extends ExceptionsBaseComponent {

  /**
   * Tests that the exception can be thrown.
   */
  @Test(expected = UnauthorizedException.class)
  public void testUnauthorizedException_Thrown() {
    throw new UnauthorizedException("Unknown Identity.");
  }

  /**
   * Tests that the exception can be caught.
   */
  @Test
  public void testUnauthorizedException_Caught() {
    try {
      throw new UnauthorizedException("Unknown Identity.");
    }
    catch (UnauthorizedException ex) {
      assertThat(ex.getMessage())
          .isEqualTo("Unknown Identity.");
    }
  }
}
