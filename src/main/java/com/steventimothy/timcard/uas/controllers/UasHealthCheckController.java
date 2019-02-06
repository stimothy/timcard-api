package com.steventimothy.timcard.uas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The UasHealthCheckController Class</h1>
 * <p>This class holds the endpoint used in the UAS health checks.</p>
 */
@Slf4j
@RequestMapping("/uas/health")
@RestController
public class UasHealthCheckController {

  /**
   * The health check endpoint.
   *
   * @return Ok if the endpoint is reached.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAmsHealth() {
    log.info("[200] GET: /uas/health - Response: body={}", "Ok");
    return ResponseEntity.ok("Ok");
  }
}
