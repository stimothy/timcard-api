package com.steventimothy.timcard.uas.controllers;

import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.uas.services.UserAuthorizationService;
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
 * <h1>The AdminUserAuthorizationController Class</h1>
 * <p>This class holds the admin endpoints used in the UAS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/uas/admin")
@RestController
public class AdminUserAuthorizationController {

  /**
   * The utility used to verify user permissions.
   */
  private IdentityUtil identityUtil;
  /**
   * The utility to map exceptions to responses.
   */
  private ExceptionMapper exceptionMapper;
  /**
   * The service layer for the UAS system that holds the logic for the UAS endpoints.
   */
  private UserAuthorizationService userAuthorizationService;

  /**
   * Gets a new user id from the database.
   * @param authorizationHeader The session id of the user requesting the id.
   * @return The new user id retrieved from the database.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getNewUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
    try {
      this.identityUtil.validateUserPermissions(authorizationHeader, getNewUserIdPermissions());
      UserId userId = userAuthorizationService.getNewUserId();
      return ResponseEntity.ok(userId);
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("GET", "/uas/admin", authorizationHeader, ex);
    }
  }

  /**
   * Frees up a user id in the database.
   * @param authorizationHeader The session id of the user that wants to free the user id up.
   * @param userId The user id that needs to be freed.
   * @return Returns ok if it was successful.
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity freeUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                   @RequestBody UserId userId) {
    try {
      this.identityUtil.validateUserPermissions(authorizationHeader, getFreeUserIdPermission());
      userAuthorizationService.freeUserId(userId);
      return ResponseEntity.ok().build();
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("GET", "/uas/admin", authorizationHeader, ex);
    }
  }

  /**
   * Gets the permissions needed to obtain a new user id.
   * @return The list of permissions needed to obtain a new user id.
   */
  private List<Permission> getNewUserIdPermissions() {
    return Collections.singletonList(Permission.SUPER_ADMIN);
  }

  /**
   * Gets the list of permissions needed to free up a user id in the database.
   * @return The list of permissions needed to free up the user id in the database.
   */
  private List<Permission> getFreeUserIdPermission() {
    return Collections.singletonList(Permission.SUPER_ADMIN);
  }
}
