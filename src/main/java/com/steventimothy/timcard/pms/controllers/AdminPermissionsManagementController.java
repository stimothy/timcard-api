package com.steventimothy.timcard.pms.controllers;

import com.steventimothy.timcard.pms.services.PermissionManagementService;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.utils.mappers.ExceptionMapper;
import com.steventimothy.timcard.utils.mappers.IdMapper;
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
 * <h1>The AdminPermissionManagementController Class</h1>
 * <p>This class holds the admin endpoints used in the PMS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/pms/admin")
@RestController
public class AdminPermissionsManagementController {

  /**
   * The service layer for the PMS system.
   */
  private PermissionManagementService permissionManagementService;
  /**
   * Maps exceptions to responses.
   */
  private ExceptionMapper exceptionMapper;
  /**
   * Mapper used to map ids between encoded values, raw values, and class values.
   */
  private IdMapper idMapper;

  /**
   * Checks to see if the user has the correct permissions.
   *
   * @param authorizationHeader The authorization sessionId.
   * @param session             The sessionId of the user.
   * @param permissions         The permissions the user needs.
   * @return Ok if they do.
   */
  @PostMapping(value = "/{session}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity hasPermissions(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                       @PathVariable("session") String session,
                                       @RequestBody List<Permission> permissions) {
    try {
      SessionId authorizationSession = idMapper.mapEncodedValueToSessionId(authorizationHeader);
      SessionId sessionId = idMapper.mapEncodedValueToSessionId(session);

      this.permissionManagementService.checkPermissions(authorizationSession, Collections.singletonList(Permission.ADMIN));
      this.permissionManagementService.checkPermissions(sessionId, permissions);

      log.info("[200] POST: /pms/{} - sessionId={} - Response: OK", session, session);
      return ResponseEntity.ok().build();
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("POST", "/pms/" + session, session, ex);
    }
  }

  /**
   * Checks to see if the user has the correct permissions.
   *
   * @param authorizationHeader The authorization sessionId.
   * @param session             The sessionId of the user.
   * @param permissions         The permissions the user needs.
   * @return Ok if they do.
   */
  @PostMapping(value = "/roles/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addPermission(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                       @PathVariable("userId") String userId,
                                       @RequestBody Role role) {
    try {
      SessionId sessionId = idMapper.mapEncodedValueToSessionId(authorizationHeader);
      this.permissionManagementService.checkPermissions(sessionId, Collections.singletonList(Permission.ADMIN));

      this.permissionManagementService.addRole(idMapper.mapEncodedValueToUserId(userId), role);

      log.info("[200] POST: /pms/{} - sessionId={} - Response: OK", sessionId.getEncodedValue(), sessionId);
      return ResponseEntity.ok().build();
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("POST", "/pms/admin/roles/userId" + authorizationHeader, authorizationHeader, ex);
    }
  }
}
