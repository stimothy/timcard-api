package com.steventimothy.timcard.pms.controllers;

import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.junit.Assert.fail;

public class AdminPermissionsManagementControllerTest extends ControllersBaseComponent {

  /**
   * Tests that the user with the correct permissions response with ok.
   */
  @Test
  public void testHasPermissions_Valid() {
    fail("Tests could not be implemented until a user can log in.");
  }

  /**
   * tests that a bad authorization header responds with a 400.
   */
  @Test
  public void testHasPermissions_Invalid_400_AuthorizationHeader() {
    SessionId sessionId = createLocalGeneralSessionId();

    ResponseEntity responseEntity = super.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getPmsHost() + "/admin/" + sessionId.getEncodedValue())
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, "badSessionId")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Collections.singletonList(Permission.PUBLIC)), UserId.class);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * tests that a bad sessionId header responds with a 400.
   */
  @Test
  public void testHasPermissions_Invalid_400_SessionId() {
    ResponseEntity responseEntity = super.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getPmsHost() + "/admin/" + "badSessionId")
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, getSuperAdminSessionId().getEncodedValue())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Collections.singletonList(Permission.PUBLIC)), UserId.class);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * tests that an authorization header with the incorrect permissions responds with a 403.
   */
  @Test
  public void testHasPermissions_Invalid_403_AuthorizationHeader() {
    fail("Tests could not be implemented until a user can log in.");
  }

  /**
   * tests that a session id with the incorrect permissions responds with a 403.
   */
  @Test
  public void testHasPermissions_Invalid_403_SessionId() {
    fail("Tests could not be implemented until a user can log in.");
  }
}
