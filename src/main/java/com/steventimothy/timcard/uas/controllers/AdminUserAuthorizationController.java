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

  private IdentityUtil identityUtil;
  private ExceptionMapper exceptionMapper;
  private UserAuthorizationService userAuthorizationService;

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

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity freeUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                   @RequestBody UserId userId) {
    try {
      this.identityUtil.validateUserPermissions(authorizationHeader, getNewUserIdPermissions());
      userAuthorizationService.freeUserId(userId);
      return ResponseEntity.ok().build();
    }
    catch (Exception ex) {
      return this.exceptionMapper.mapExceptionToResponse("GET", "/uas/admin", authorizationHeader, ex);
    }
  }

  private List<Permission> getNewUserIdPermissions() {
    return Collections.singletonList(Permission.SUPER_ADMIN);
  }

  private List<Permission> getFreeUserIdPermission() {
    return Collections.singletonList(Permission.SUPER_ADMIN);
  }
}
