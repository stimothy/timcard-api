package com.steventimothy.timcard.uas.controllers;

import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAuthorizationControllerTest extends ControllersBaseComponent {

  /**
   * Tests that a general session id can be retrieved from the system.
   */
  @Test
  public void testGetGeneralSessionId() {
    ResponseEntity<SessionId> responseEntity = requestGetGeneralSessionId();

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();
  }
}
