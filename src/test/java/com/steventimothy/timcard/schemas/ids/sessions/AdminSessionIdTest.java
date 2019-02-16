package com.steventimothy.timcard.schemas.ids.sessions;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminSessionIdTest extends SessionsBaseComponent {

  /**
   * Tests that the right data is returned from the getEncodedValue method.
   */
  @Test
  public void testAdminSessionId_GetEncodedValue() {
    String rawId = UUID.randomUUID().toString();
    SessionId id = createLocalAdminSessionId(rawId);

    assertThat(id.getEncodedValue())
        .isNotNull()
        .startsWith("session.admin.")
        .endsWith(rawId);

    SessionId id2 = createLocalAdminSessionId(null);

    assertThat(id2.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that the right data is returned from the toString method.
   */
  @Test
  public void testAdminSessionId_ToString() {
    String rawId = UUID.randomUUID().toString();
    SessionId id = createLocalAdminSessionId(rawId);

    assertThat(id.toString())
        .isNotNull()
        .startsWith("AdminSessionId(")
        .contains(id.getEncodedValue());

    SessionId id2 = createLocalAdminSessionId(null);

    assertThat(id2.toString())
        .isNotNull()
        .startsWith("AdminSessionId(")
        .contains("null");
  }

  /**
   * Tests that the constructor works.
   */
  @Test
  public void testAdminSessionId_Constructor() {
    SessionId id = createLocalAdminSessionId();

    assertThat(id)
        .isNotNull();

    SessionId id2 = createLocalAdminSessionId(null);

    assertThat(id2)
        .isNotNull();
  }
}
