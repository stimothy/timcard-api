package com.steventimothy.timcard;

import com.steventimothy.timcard.schemas.ids.sessions.AdminSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.sessions.UserSessionId;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseComponent {

  /**
   * Used to make rest calls.
   */
  @Autowired
  protected TestRestTemplate restTemplate;

  /**
   * Creates a local general session id.
   *
   * @return a general session Id.
   */
  protected SessionId createLocalGeneralSessionId() {
    return createLocalGeneralSessionId(UUID.randomUUID());
  }

  /**
   * Creates a local general session id.
   *
   * @param uuid The given id.
   * @return The general sessionId with the given id.
   */
  protected SessionId createLocalGeneralSessionId(UUID uuid) {
    return new GeneralSessionId(uuid);
  }

  /**
   * Creates a local user session id.
   *
   * @return a user session Id.
   */
  protected SessionId createLocalUserSessionId() {
    return createLocalUserSessionId(UUID.randomUUID());
  }

  /**
   * Creates a local user session id.
   *
   * @param uuid The given id.
   * @return The user sessionId with the given id.
   */
  protected SessionId createLocalUserSessionId(UUID uuid) {
    return new UserSessionId(uuid);
  }

  /**
   * Creates a local admin session id.
   *
   * @return a admin session Id.
   */
  protected SessionId createLocalAdminSessionId() {
    return createLocalAdminSessionId(UUID.randomUUID());
  }

  /**
   * Creates a local admin session id.
   *
   * @param uuid The given id.
   * @return The admin sessionId with the given id.
   */
  protected SessionId createLocalAdminSessionId(UUID uuid) {
    return new AdminSessionId(uuid);
  }

  /**
   * Creates a local general user id.
   *
   * @return a general user Id.
   */
  protected UserId createLocalGeneralUserId() {
    return createLocalGeneralUserId(4L);
  }

  /**
   * Creates a local general user id.
   *
   * @return a general user Id.
   */
  protected UserId createAltLocalGeneralUserId() {
    return createLocalGeneralUserId(5L);
  }

  /**
   * Creates a local general user id.
   *
   * @param rawId The given id.
   * @return The general userId with the given id.
   */
  protected UserId createLocalGeneralUserId(Long rawId) {
    return new GeneralUserId(rawId);
  }

  /**
   * Creates a local admin user id.
   *
   * @return a admin user Id.
   */
  protected UserId createLocalAdminUserId() {
    return createLocalAdminUserId(1L);
  }

  /**
   * Creates a local admin user id.
   *
   * @param rawId The given id.
   * @return The admin userId with the given id.
   */
  protected UserId createLocalAdminUserId(Long rawId) {
    return new AdminUserId(rawId);
  }

  /**
   * Gets a general session id.
   *
   * @return The general session id.
   */
  protected String getGeneralSessionId() {
    ResponseEntity<String> responseEntity = requestGetGeneralSessionId();

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets a general session id.
   *
   * @return The response to the rest call.
   */
  protected ResponseEntity<String> requestGetGeneralSessionId() {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getUasHost())
        .build().toUri())
        .build(), String.class);
  }

  /**
   * Asserts that the response from a rest call has the given status.
   *
   * @param responseEntity The response of the rest call.
   * @param status         The expected status.
   */
  protected void assertStatus(ResponseEntity responseEntity, HttpStatus status) {
    assertThat(responseEntity.getStatusCode())
        .isEqualTo(status);
  }

  /**
   * Gets the AMS base URL.
   *
   * @return The AMS URL.
   */
  protected String getAmsHost() {
    return "/ams";
  }

  /**
   * Gets the PMS base URL.
   *
   * @return The PMS URL.
   */
  protected String getPmsHost() {
    return "/pms";
  }

  /**
   * Gets the UAS base URL.
   *
   * @return The UAS URL.
   */
  protected String getUasHost() {
    return "/uas";
  }
}
