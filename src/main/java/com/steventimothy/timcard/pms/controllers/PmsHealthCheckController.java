package com.steventimothy.timcard.pms.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The PmsHealthCheckController Class</h1>
 * <p>This class holds the endpoint used in the PMS health checks.</p>
 */
@Slf4j
@RequestMapping("/pms/health")
@RestController
public class PmsHealthCheckController {

//  /**
//   * The health check endpoint.
//   *
//   * @return Ok if the endpoint is reached.
//   */
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getAmsHealth() {
//    log.info("[200] GET: /pms/health - Response: body={}", "Ok");
//    return ResponseEntity.ok("Ok");
//  }
}
