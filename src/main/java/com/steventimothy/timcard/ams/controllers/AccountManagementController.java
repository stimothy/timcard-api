package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.ams.services.AccountManagementService;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.ids.users.UserIdType;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.users.User;
import com.steventimothy.timcard.utils.mappers.ExceptionMapper;
import com.steventimothy.timcard.utils.validation.IdentityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * <h1>The AccountManagementController Class</h1>
 * <p>This class holds the endpoints used in the AMS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ams")
@RestController
public class AccountManagementController {

  /**
   * The service layer holding the logic for the AMS endpoints.
   */
  private AccountManagementService accountManagementService;
  /**
   * The utility responsible for validating user permissions.
   */
  private IdentityUtil identityUtil;
  /**
   * Maps exceptions to responses.
   */
  private ExceptionMapper exceptionMapper;

  /**
   * Creates an admin user in the database.
   * Needed Permissions: CREATE_USER
   *
   * @param authorizationHeader The authorization sessionId.
   * @param user                The user they want to create.
   * @return The userId of the created user.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                        @RequestBody User user) {
    try {
      SessionId sessionId = this.identityUtil.validateUserPermissions(authorizationHeader, getCreateUserPermissions());

      UserId userId = this.accountManagementService.createUser(user, UserIdType.GENERAL);

      log.info("[200] POST: /ams - sessionId={} - Response: userId={}", sessionId, userId);
      return ResponseEntity.ok(userId);
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("POST", "/ams", authorizationHeader, ex);
    }
  }

  /**
   * Gets the user permissions needed for creating a user.
   *
   * @return The list of permissions needed for creating the user.
   */
  private List<Permission> getCreateUserPermissions() {
    return Collections.singletonList(Permission.CREATE_USER);
  }


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
