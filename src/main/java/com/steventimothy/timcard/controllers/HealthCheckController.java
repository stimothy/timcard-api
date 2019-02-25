package com.steventimothy.timcard.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The HealthCheckController Class</h1>
 * <p>This class holds the endpoint used in health checks.</p>
 */
@Slf4j
@RequestMapping("/health")
@RestController
public class HealthCheckController {

//  /**
//   * The health check endpoint.
//   *
//   * @return Ok if the endpoint is reached.
//   */
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getHealth() {
//    log.info("[200] GET: /health - Response: body={}", "Ok");
//    return ResponseEntity.ok("Ok");
//  }
}
