package com.steventimothy.timcard.ams.controllers;

import com.steventimothy.timcard.ams.services.AccountManagementService;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.schemas.users.User;
import com.steventimothy.timcard.utils.validation.IdentityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
   * The utility responsible for identifying the user and its permissions.
   */
  private IdentityUtil identityUtil;
  private AccountManagementService accountManagementService;

  /**
   * Creates a user in the database.
   *
   * @param authorizationHeader The sessionId of the user.
   * @param user                The user they want to create.
   * @return The userId of the created user, or a bad request if it could not.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                   @RequestBody User user) {

    try {
      SessionId sessionId = this.identityUtil.validate(authorizationHeader, Role.GENERAL.getPermissions());

      UserId userId = this.accountManagementService.createUser(user);

      if (userId.getEncodedValue() != null) {
        log.info("[200] POST: /ams - sessionId={} - Response: userId={}", sessionId, userId);
        return ResponseEntity.ok(userId.getEncodedValue());
      }
      else {
        log.warn("[400] POST: /ams - sessionId={} - Invalid data", sessionId);
        return ResponseEntity.badRequest().build();
      }
    }
    catch (UnauthorizedException ex) {
      log.warn("[401] POST: /ams - sessionId={} - {}", authorizationHeader, ex.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
