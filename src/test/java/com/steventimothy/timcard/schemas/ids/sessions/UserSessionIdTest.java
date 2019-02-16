package com.steventimothy.timcard.schemas.ids.sessions;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSessionIdTest extends SessionsBaseComponent {

  /**
   * Tests that the right data is returned from the getEncodedValue method.
   */
  @Test
  public void testUserSessionId_GetEncodedValue() {
    String rawId = UUID.randomUUID().toString();
    SessionId id = createLocalUserSessionId(rawId);

    assertThat(id.getEncodedValue())
        .isNotNull()
        .startsWith("session.user.")
        .endsWith(rawId);

    SessionId id2 = createLocalUserSessionId(null);

    assertThat(id2.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that the right data is returned from the toString method.
   */
  @Test
  public void testUserSessionId_ToString() {
    String rawId = UUID.randomUUID().toString();
    SessionId id = createLocalUserSessionId(rawId);

    assertThat(id.toString())
        .isNotNull()
        .startsWith("UserSessionId(")
        .contains(id.getEncodedValue());

    SessionId id2 = createLocalUserSessionId(null);

    assertThat(id2.toString())
        .isNotNull()
        .startsWith("UserSessionId(")
        .contains("null");
  }

  /**
   * Tests that the constructor works.
   */
  @Test
  public void testUserSessionId_Constructor() {
    SessionId id = createLocalUserSessionId();

    assertThat(id)
        .isNotNull();

    SessionId id2 = createLocalUserSessionId(null);

    assertThat(id2)
        .isNotNull();
  }
}
