package com.steventimothy.timcard.pms.services;

import com.steventimothy.timcard.repository.timcard.permissions.PermissionsDataService;
import com.steventimothy.timcard.repository.timcard.sessions.SessionsDataService;
import com.steventimothy.timcard.repository.timcard.user_roles.UserRolesDataService;
import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>The PermissionManagementService Class</h1>
 * <p>This class holds the logic for the PMS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class PermissionManagementService {

  /**
   * The data service layer for the permissions data table.
   */
  private PermissionsDataService permissionsDataService;
  /**
   * The data service layer for the sessions data table.
   */
  private SessionsDataService sessionsDataService;
  /**
   * The data service layer for the user_roles data table.
   */
  private UserRolesDataService userRolesDataService;

  /**
   * Checks that a user has the appropriate permissions pertaining to the list of roles
   * passed in.
   *
   * @param sessionId   The session id of the user to check for permissions.
   * @param permissions The list of permissions the user needs.
   */
  public void checkPermissions(SessionId sessionId, List<Permission> permissions) {
    permissions = permissions.stream()
        .filter(this::isCheckablePermission)
        .collect(Collectors.toList());

    if (!permissions.isEmpty()) {
      UserId userId = getUserIdFromSession(sessionId);

      if (!permissionsDataService.getUserPermissions(userId).containsAll(permissions)) {
        throw new ForbiddenException("Permission Denied.");
      }
    }
  }

  /**
   * Adds a role to a user.
   * @param userId The user id of the user to add a role to.
   * @param role The role to add to the user.
   */
  public void addRole(UserId userId, Role role) {
    this.userRolesDataService.addRole(userId, role);
  }

  /**
   * Checks to see if it is a permission that needs a database lookup.
   * @param permission The permission to check.
   * @return True if the permission needs database lookups.
   */
  private boolean isCheckablePermission(Permission permission) {
    return (!Permission.PUBLIC.equals(permission));
  }

  /**
   * Gets the user Id from the session.
   *
   * @param sessionId The session id of the userId wanted.
   * @return The user id matching the session id.
   */
  private UserId getUserIdFromSession(SessionId sessionId) {
    return sessionsDataService.getUserId(sessionId);
  }
}
