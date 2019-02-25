package com.steventimothy.timcard.uas.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The UserAuthorizationController Class</h1>
 * <p>This class holds the endpoints used in the UAS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/uas")
@RestController
public class UserAuthorizationController {

//  /**
//   * The class that contains the logic for these endpoints.
//   */
//  private UserAuthorizationService userAuthorizationService;
//
//  /**
//   * Gets a general session id to use in the system.
//   *
//   * @return The general session id.
//   */
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getGeneralSessionId() {
//    SessionId sessionId = this.userAuthorizationService.createGeneralSessionId();
//
//    log.info("[200] GET: /uas - Response: body={}", sessionId);
//    return ResponseEntity.ok(sessionId);
//  }
}
