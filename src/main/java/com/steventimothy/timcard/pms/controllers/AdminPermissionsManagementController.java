package com.steventimothy.timcard.pms.controllers;

import com.steventimothy.timcard.pms.services.PermissionManagementService;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.utils.mappers.ExceptionMapper;
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
   * Checks to see if the user has the correct permissions.
   * @param authorizationHeader The authorization sessionId.
   * @param session The sessionId of the user.
   * @param roles The roles pertaining to the permissions the user needs.
   * @return Ok if they do.
   */
  @PostMapping(value = "/{session}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity hasPermissions(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                       @PathVariable("session") String session,
                                       @RequestBody List<Role> roles) {
    try {
      this.permissionManagementService.checkPermissions(authorizationHeader, Collections.singletonList(Role.ADMIN));
      this.permissionManagementService.checkPermissions(session, roles);

      log.info("[200] POST: /pms/{} - sessionId={} - Response: OK", session, session);
      return ResponseEntity.ok().build();
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("POST", "/pms/" + session, session, ex);
    }
  }
}
