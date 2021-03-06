package com.steventimothy.timcard.ams.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The AccountManagementController Class</h1>
 * <p>This class holds the endpoints used in the AMS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ams")
@RestController
public class AccountManagementController {

//  /**
//   * The service layer holding the logic for the AMS endpoints.
//   */
//  private AccountManagementService accountManagementService;
//  /**
//   * The utility responsible for validating user permissions.
//   */
//  private IdentityUtil identityUtil;
//  /**
//   * Maps exceptions to responses.
//   */
//  private ExceptionMapper exceptionMapper;
//
//  /**
//   * Creates an admin user in the database.
//   * Needed Permissions: PUBLIC
//   *
//   * @param authorizationHeader The authorization sessionId.
//   * @param user                The user they want to create.
//   * @return The userId of the created user.
//   */
//  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
//                                   @RequestBody User user) {
//    try {
//      SessionId sessionId = this.identityUtil.validateUserPermissions(authorizationHeader, getCreateUserPermissions());
//
//      UserId userId = this.accountManagementService.createUser(user, UserIdType.GENERAL);
//
//      log.info("[200] POST: /ams - sessionId={} - Response: userId={}", sessionId, userId);
//      return ResponseEntity.ok(userId);
//    }
//    catch (Exception ex) {
//      return this.exceptionMapper.mapExceptionToResponse("POST", "/ams", authorizationHeader, ex);
//    }
//  }
//
//  /**
//   * Gets the user permissions needed for creating a user.
//   *
//   * @return The list of permissions needed for creating the user.
//   */
//  private List<Permission> getCreateUserPermissions() {
//    return Collections.singletonList(Permission.PUBLIC);
//  }
}
