package com.steventimothy.timcard.schemas.users;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class UserTest extends UsersBaseComponent {

  /**
   * Tests the lombok.
   */
  @Test
  public void testUser() {
    EqualsVerifier.forClass(User.class)
        .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS)
        .verify();
  }
}
