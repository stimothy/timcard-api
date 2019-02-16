package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public class AdminAccountManagementControllerTest extends ControllersBaseComponent {

  @Test
  public void testing() {
    User user = new User();
    SessionId sessionId = createLocalAdminSessionId(UUID.randomUUID().toString());
    this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsHost() + "/admin")
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, sessionId.getEncodedValue())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), String.class);
  }
}
