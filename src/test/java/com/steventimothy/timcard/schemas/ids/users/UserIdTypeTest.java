package com.steventimothy.timcard.schemas.ids.users;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class UserIdTypeTest extends UsersBaseComponent {

  /**
   * Tests the lombok.
   */
  @Test
  public void testUserIdType() {
    EqualsVerifier.forClass(UserIdType.class)
        .verify();
  }
}
