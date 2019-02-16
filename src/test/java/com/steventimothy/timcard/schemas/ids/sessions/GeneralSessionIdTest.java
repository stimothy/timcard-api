package com.steventimothy.timcard.schemas.ids.sessions;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralSessionIdTest extends SessionsBaseComponent {

  /**
   * Tests that the right data is returned from the getEncodedValue method.
   */
  @Test
  public void testGeneralSessionId_GetEncodedValue() {
    String rawId = UUID.randomUUID().toString();
    SessionId id = createLocalGeneralSessionId(rawId);

    assertThat(id.getEncodedValue())
        .isNotNull()
        .startsWith("session.general.")
        .endsWith(rawId);

    SessionId id2 = createLocalGeneralSessionId(null);

    assertThat(id2.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that the right data is returned from the toString method.
   */
  @Test
  public void testGeneralSessionId_ToString() {
    String rawId = UUID.randomUUID().toString();
    SessionId id = createLocalGeneralSessionId(rawId);

    assertThat(id.toString())
        .isNotNull()
        .startsWith("GeneralSessionId")
        .contains(id.getEncodedValue());

    SessionId id2 = createLocalGeneralSessionId(null);

    assertThat(id2.toString())
        .isNotNull()
        .startsWith("GeneralSessionId(")
        .contains("null");
  }

  /**
   * Tests that the constructor works.
   */
  @Test
  public void testGeneralSessionId_Constructor() {
    SessionId id = createLocalGeneralSessionId();

    assertThat(id)
        .isNotNull();

    SessionId id2 = createLocalGeneralSessionId(null);

    assertThat(id2)
        .isNotNull();
  }
}
