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
//   * The utility responsible for identifying the user and its permissions.
//   */
//  private IdentityUtil identityUtil;
//  /**
//   * The service class that handles all of the logistics for the AMS endpoints.
//   */
//  private AccountManagementService accountManagementService;
//  /**
//   * The utility responsible for mapping exceptions to response entities.
//   */
//  private ExceptionMapper exceptionMapper;
////  /**
////   * Utility to map ids.
////   */
////  private IdMapper idMapper;
////
//  /**
//   * Creates a user in the database.
//   *
//   * @param authorizationHeader The sessionId of the user.
//   * @param user                The user they want to create.
//   * @return The userId of the created user.
//   */
//  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
//                                   @RequestBody User user) {
//    try {
//      SessionId sessionId = this.identityUtil.validate(authorizationHeader, getCreateUserPermissions());
//
//      UserId userId = this.accountManagementService.createUser(user);
//
//      log.info("[200] POST: /ams - sessionId={} - Response: userId={}", sessionId, userId);
//      return ResponseEntity.ok(userId.getEncodedValue());
//    }
//    catch (Exception ex) {
//      return this.exceptionMapper.mapExceptionToResponse("POST", "/ams", authorizationHeader, ex);
//    }
//  }
//
////  /**
////   * Deletes a user in the system.
////   *
////   * @param authorizationHeader The session Id of the user.
////   * @param id                  The id of the user to delete.
////   * @return Ok if the deletion was successful.
////   */
////  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
////  public ResponseEntity deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
////                                   @PathVariable("id") String id) {
////    try {
////      SessionId sessionId = this.identityUtil.validate(authorizationHeader, getDeleteUserPermissions());
////
////      this.accountManagementService.deleteUser(this.idMapper.mapEncodedValueToUserId(id), sessionId);
////
////      log.info("[200] DELETE: /ams/{} - sessionId={} - Response: Ok", id, sessionId);
////      return ResponseEntity.ok().build();
////    }
////    catch (Exception ex) {
////      return this.exceptionMapper.mapExceptionToResponse("DELETE", "/ams/" + id, authorizationHeader, ex);
////    }
////  }
//
//  /**
//   * Gets a list of permissions for the createUser endpoint.
//   *
//   * @return A list of permissions for the createUser endpoint.
//   */
//  private List<Permission> getCreateUserPermissions() {
//    return Collections.singletonList(
//        Permission.CREATE_USER
//    );
//  }
////
////  /**
////   * Gets a list of permissions for the deleteUser endpoint.
////   *
////   * @return A list of permissions for the deleteUser endpoint.
////   */
////  private List<Permission> getDeleteUserPermissions() {
////    return Collections.singletonList(
////        Permission.DELETE_USER
////    );
////  }
}
