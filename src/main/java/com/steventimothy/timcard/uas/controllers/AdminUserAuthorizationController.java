package com.steventimothy.timcard.uas.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The AdminUserAuthorizationController Class</h1>
 * <p>This class holds the admin endpoints used in the UAS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/uas/admin")
@RestController
public class AdminUserAuthorizationController {

//  /**
//   * The utility used to verify user permissions.
//   */
//  private IdentityUtil identityUtil;
//  /**
//   * The utility to map exceptions to responses.
//   */
//  private ExceptionMapper exceptionMapper;
//  /**
//   * The service layer for the UAS system that holds the logic for the UAS endpoints.
//   */
//  private UserAuthorizationService userAuthorizationService;
//
//  /**
//   * Gets a new user id from the database.
//   * @param authorizationHeader The session id of the user requesting the id.
//   * @return The new user id retrieved from the database.
//   */
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getNewUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
//    try {
//      this.identityUtil.validateUserPermissions(authorizationHeader, getNewUserIdPermissions());
//      UserId userId = userAuthorizationService.getNewUserId();
//      return ResponseEntity.ok(userId);
//    }
//    catch (Exception ex) {
//      return this.exceptionMapper.mapExceptionToResponse("GET", "/uas/admin", authorizationHeader, ex);
//    }
//  }
//
//  /**
//   * Frees up a user id in the database.
//   * @param authorizationHeader The session id of the user that wants to free the user id up.
//   * @param userId The user id that needs to be freed.
//   * @return Returns ok if it was successful.
//   */
//  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity freeUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
//                                   @RequestBody UserId userId) {
//    try {
//      this.identityUtil.validateUserPermissions(authorizationHeader, getFreeUserIdPermission());
//      userAuthorizationService.freeUserId(userId);
//      return ResponseEntity.ok().build();
//    }
//    catch (Exception ex) {
//      return this.exceptionMapper.mapExceptionToResponse("POST", "/uas/admin", authorizationHeader, ex);
//    }
//  }
//
//  /**
//   * Marks a user id as being used.
//   * @param authorizationHeader The session id of the user that wants to mark the user id as used.
//   * @param userId The user id to mark used.
//   * @return Returns ok of it was successful.
//   */
//  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity markUserIdUsed(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
//                                       @RequestBody UserId userId) {
//    try {
//      this.identityUtil.validateUserPermissions(authorizationHeader, getMarkUserIdUsedPermission());
//      userAuthorizationService.markUserIdUsed(userId);
//      return ResponseEntity.ok().build();
//    }
//    catch (Exception ex) {
//      return this.exceptionMapper.mapExceptionToResponse("PUT", "/uas/admin", authorizationHeader, ex);
//    }
//  }
//
//  /**
//   * Gets the permissions needed to obtain a new user id.
//   * @return The list of permissions needed to obtain a new user id.
//   */
//  private List<Permission> getNewUserIdPermissions() {
//    return Collections.singletonList(Permission.SUPER_ADMIN);
//  }
//
//  /**
//   * Gets the list of permissions needed to free up a user id in the database.
//   * @return The list of permissions needed to free up the user id in the database.
//   */
//  private List<Permission> getFreeUserIdPermission() {
//    return Collections.singletonList(Permission.SUPER_ADMIN);
//  }
//
//  /**
//   * Gets the list of permissions needed to mark a user id in the database used.
//   * @return The list of permissions needed to mark a user id in the database as used.
//   */
//  private List<Permission> getMarkUserIdUsedPermission() {
//    return Collections.singletonList(Permission.SUPER_ADMIN);
//  }
}
