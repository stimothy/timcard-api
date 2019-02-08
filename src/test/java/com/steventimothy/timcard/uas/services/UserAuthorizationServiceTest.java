package com.steventimothy.timcard.uas.services;

import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAuthorizationServiceTest extends ServicesBaseComponent {

  /**
   * Tests that a general session id is returned from the function.
   */
  @Test
  public void testCreateGeneralSessionId() {
    SessionId sessionId = userAuthorizationService.createGeneralSessionId();

    assertThat(sessionId)
        .isNotNull()
        .isInstanceOf(GeneralSessionId.class);
    assertThat(sessionId.getEncodedValue())
        .isNotNull();
  }
}
