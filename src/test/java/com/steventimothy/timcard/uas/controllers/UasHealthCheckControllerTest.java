package com.steventimothy.timcard.uas.controllers;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class UasHealthCheckControllerTest extends ControllersBaseComponent {

  /**
   * Tests the health of the UAS system.
   */
  @Test
  public void testHealth() {
    ResponseEntity<String> responseEntity = super.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getUasHost() + "/health")
        .build().toUri())
        .build(), String.class);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isEqualTo("Ok");
  }
}
