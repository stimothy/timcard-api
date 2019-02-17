package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.ams.services.AccountManagementService;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.ids.users.UserIdType;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
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
 * <h1>The AdminAccountManagementController Class</h1>
 * <p>This class holds the admin endpoints used in the AMS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ams/admin")
@RestController
public class AdminAccountManagementController {

  /**
   * The service class for the AMS endpoints.
   */
  private AccountManagementService accountManagementService;
  /**
   * The utility that verifies user has permission to use this endpoint.
   */
  private IdentityUtil identityUtil;
  /**
   * The utility that maps exceptions to responses.
   */
  private ExceptionMapper exceptionMapper;

  /**
   * Creates an admin user in the database.
   * Needed Permissions: SUPER_ADMIN
   *
   * @param authorizationHeader The authorization sessionId.
   * @param user                The user they want to create.
   * @return The userId of the created user.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createAdminUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                        @RequestBody User user) {
    try {
      SessionId sessionId = this.identityUtil.validateUserPermissions(authorizationHeader, getCreateAdminUserPermissions());

      UserId userId = this.accountManagementService.createUser(user, UserIdType.ADMIN);

      log.info("[200] POST: /ams - sessionId={} - Response: userId={}", sessionId, userId);
      return ResponseEntity.ok(userId.getEncodedValue());
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("POST", "/ams/admin", authorizationHeader, ex);
    }
  }

  /**
   * Gets the roles needed to create an admin user.
   * @return A list of roles associated with creating an admin user.
   */
  private List<Permission> getCreateAdminUserPermissions() {
    return Collections.singletonList(Permission.CREATE_ADMIN_USER);
  }
}
