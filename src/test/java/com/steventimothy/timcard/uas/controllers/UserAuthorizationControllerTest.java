package com.steventimothy.timcard.uas.controllers;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class UserAuthorizationControllerTest extends ControllersBaseComponent {

  /**
   * Tests that a general session id can be retrieved from the system.
   */
  @Test
  public void testGetGeneralSessionId() {
    ResponseEntity<String> responseEntity = requestGetGeneralSessionId();

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
  }

  @Test
  public void testGetUserIdFromSession_Valid() {
    fail("Test not implemented yet.");
  }
}
