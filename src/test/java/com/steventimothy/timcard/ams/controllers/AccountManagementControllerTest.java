package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class AccountManagementControllerTest extends ControllersBaseComponent {

  @Test
  public void testCreateUser_Valid() {
    fail("Test not implemented yet.");
  }

  @Test
  public void testCreateUser_400() {
    fail("Test not implemented yet.");
  }

  @Test
  public void testCreateUser_401() {
    fail("Test not implemented yet.");
  }

  @Test
  public void testCreateUser_409() {
    fail("Test not implemented yet.");
  }

//  /**
//   * Tests that a user can be created.
//   */
//  @Test
//  public void testCreateUser_Valid() {
//    String sessionId = getGeneralSessionId();
//    User user = createLocalUser();
//
//    ResponseEntity<String> responseEntity = requestCreateUser(user, sessionId);
//
//    assertStatus(responseEntity, HttpStatus.OK);
//    assertThat(responseEntity.getBody())
//        .isNotNull();
//    assertThat(responseEntity.getBody())
//        .isEqualTo(user.userId().getEncodedValue());
//  }
//
//  /**
//   * Tests that a user with bad data cannot be created and responds with a 400.
//   */
//  @Test
//  public void testCreateUser_400() {
//    String sessionId = getGeneralSessionId();
//    User user = createLocalUser();
//    user.username(null);
//
//    ResponseEntity<String> responseEntity = requestCreateUser(user, sessionId);
//
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a user without a valid session id cannot create a user and response with a 401.
//   */
//  @Test
//  public void testCreateUser_401() {
//    User user = createLocalUser();
//
//    ResponseEntity<String> responseEntity = requestCreateUser(user, "hippo");
//
//    assertStatus(responseEntity, HttpStatus.UNAUTHORIZED);
//  }
//
//  /**
//   * Tests that a user that causes conflicts in the database returns a 409.
//   */
//  @Test
//  public void testCreateUser_409() {
//    String sessionId = getGeneralSessionId();
//    User user = createLocalUser();
//
//    requestCreateUser(user, sessionId);
//    ResponseEntity<String> responseEntity = requestCreateUser(user, sessionId);
//
//    assertStatus(responseEntity, HttpStatus.CONFLICT);
//  }
//
//  /**
//   * Tests that a user can be deleted.
//   */
//  @Test
//  public void testDeleteUser_Valid() {
//    fail("Test not implemented yet.");
//  }
//
//  /**
//   * Tests that deleting a user with invalid id returns a 400.
//   */
//  @Test
//  public void testDeleteUser_400_InvalidId() {
//    fail("Test not implemented yet.");
//  }
//
//  /**
//   * Tests that deleting a user whose id is not in the database returns a 400.
//   */
//  @Test
//  public void testDeleteUser_400_IdNotInDB() {
//    fail("Test not implemented yet.");
//  }
//
//  /**
//   * Tests that a user with an unknown identity returns a 401.
//   */
//  @Test
//  public void testDeleteUser_401() {
//    fail("Test not implemented yet.");
//  }
//
//  /**
//   * Tests that a user with incorrect permissions returns a 403.
//   */
//  @Test
//  public void testDeleteUser_403() {
//    fail("Test not implemented yet.");
//  }
}
