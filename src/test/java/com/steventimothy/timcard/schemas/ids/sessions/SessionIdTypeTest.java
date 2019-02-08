package com.steventimothy.timcard.schemas.ids.sessions;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class SessionIdTypeTest extends SessionsBaseComponent {

  /**
   * Tests the lombok.
   */
  @Test
  public void testSessionIdType() {
    EqualsVerifier.forClass(SessionIdType.class)
        .verify();
  }
}
