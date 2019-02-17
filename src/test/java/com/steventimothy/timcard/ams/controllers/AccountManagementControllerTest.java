package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementControllerTest extends ControllersBaseComponent {

  /**
   * Tests that a user can be created and keeps its assigned id.
   */
  @Test
  public void testCreateUser_Valid_KeepsAssignedId() {
    User user = createLocalUser();

    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull()
        .isEqualTo(user.userId());
  }

  /**
   * Tests that a user gets created and gets a new id.
   */
  @Test
  public void testCreateUser_Valid_GetsNewId() {
    User user = createLocalUserNoId();

    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();
  }

  /**
   * Tests that the endpoint throws a 400 if bad data was given.
   */
  @Test
  public void testCreateUser_Invalid_400() {
    User user = createLocalUser();
    user.username(null);

    ResponseEntity responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that the endpoint throws a 401 if the session id was bad.
   */
  @Test
  public void testCreateUser_Invalid_401() {
    User user = createLocalUser();
    user.email(user.email().toLowerCase());
    user.password(hashPassword(user.password(), getSalt(user.username())));

    ResponseEntity responseEntity = super.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsHost())
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, "MyBadSessionId")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), UserId.class);

    assertStatus(responseEntity, HttpStatus.UNAUTHORIZED);
  }
}
