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
    UUID uuid = UUID.randomUUID();
    SessionId id = createLocalUserSessionId(uuid);

    assertThat(id.getEncodedValue())
        .isNotNull()
        .startsWith("session.user.")
        .endsWith(uuid.toString());

    SessionId id2 = createLocalUserSessionId(null);

    assertThat(id2.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that the right data is returned from the toString method.
   */
  @Test
  public void testUserSessionId_ToString() {
    UUID uuid = UUID.randomUUID();
    SessionId id = createLocalUserSessionId(uuid);

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
