package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementControllerTest extends ControllersBaseComponent {

  /**
   * Tests that a user can be created.
   */
  @Test
  public void testCreateUser_Valid() {
    String sessionId = getGeneralSessionId();
    User user = createLocalUser();

    ResponseEntity<String> responseEntity = requestCreateUser(user, sessionId);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody())
        .isEqualTo(user.userId().getEncodedValue());
  }

  /**
   * Tests that a user with bad data cannot be created and responds with a 400.
   */
  @Test
  public void testCreateUser_400() {
    String sessionId = getGeneralSessionId();
    User user = createLocalUser();
    user.username(null);

    ResponseEntity<String> responseEntity = requestCreateUser(user, sessionId);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user without a valid session id cannot create a user and response with a 401.
   */
  @Test
  public void testCreateUser_401() {
    User user = createLocalUser();

    ResponseEntity<String> responseEntity = requestCreateUser(user, "hippo");

    assertStatus(responseEntity, HttpStatus.UNAUTHORIZED);
  }
}
