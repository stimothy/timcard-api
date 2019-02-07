package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.ams.AmsBaseComponent;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ControllersBaseComponent extends AmsBaseComponent {

  /**
   * Creates a local user.
   *
   * @return A local user.
   */
  protected User createLocalUser() {
    return createLocalUser(createLocalGeneralUserId(), "testUser4", "testUser4@test.com", "ch33t@sRunFaSt");
  }

  /**
   * Creates a local user.
   *
   * @return a local user.
   */
  protected User createAltLocalUser() {
    return createLocalUser(createAltLocalGeneralUserId(), "testUser5", "testUser5@test.com", "anTsW@lk!na1ine");
  }

  /**
   * Creates a local user.
   *
   * @param userId   The userId of the user.
   * @param username The username of th euser.
   * @param email    The email of the user.
   * @param password The password of the user.
   * @return The newly created user.
   */
  protected User createLocalUser(UserId userId, String username, String email, String password) {
    return new User()
        .userId(userId)
        .username(username)
        .email(email)
        .password(password);
  }

  /**
   * Creates a user.
   *
   * @param user The user to create.
   * @return The userId of the created user.
   */
  protected String createUser(User user) {
    return createUser(user, getGeneralSessionId());
  }

  /**
   * Creates a user.
   *
   * @param user      The user to create.
   * @param sessionId The session id.
   * @return The userId of the created user.
   */
  protected String createUser(User user, String sessionId) {
    ResponseEntity<String> responseEntity = requestCreateUser(user, sessionId);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Creates a user.
   *
   * @param user      The user to create.
   * @param sessionId The session Id.
   * @return The response from the rest call.
   */
  protected ResponseEntity<String> requestCreateUser(User user, String sessionId) {
    return this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsHost())
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, sessionId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), String.class);
  }
}
