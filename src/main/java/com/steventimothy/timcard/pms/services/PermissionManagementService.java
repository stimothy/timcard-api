package com.steventimothy.timcard.pms.services;

import com.steventimothy.timcard.repository.timcard.permissions.PermissionsDataService;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
   * Checks that a user has the appropriate permissions pertaining to the list of roles
   * passed in.
   * @param session The session id of the user to check for permissions.
   * @param roles The list of roles the user needs.
   */
  public void checkPermissions(String session, List<Role> roles) {
    List<Permission> neededPermissions = permissionsDataService.getPermissionsMatchingRoles(roles);

    throw new UnsupportedOperationException("checkPermissions not implemented yet.");
  }



















//    if (!neededPermissions.isEmpty()) {
//      SessionId sessionId = this.idMapper.mapEncodedValueToSessionId(session);
//      UserId userId = this.uasClient.getUserIdFromSession(sessionId);
//      //List<Permission> userPermissions = getUserPermissions(userId);
//
////      if (!userPermissions.containsAll(neededPermissions)) {
////        throw new ForbiddenException("Permission Denied.");
////      }
//    }

//  /**
//   * The client that calls the UAS system.
//   */
//  private UasClient uasClient;
//  /**
//   * The utility for mapping ids.
//   */
//  private IdMapper idMapper;

//  private List<Permission> getUserPermissions(UserId userId) {
//    List<Role> roles = this.permissionsDataService.getUserRoles(userId);
//    return getPermissionsMatchingRoles(roles);
//  }
//
//  private List<Permission> getPermissionsMatchingRoles(List<Role> roles) {
//    return roles.stream()
//        .map(this::getRolePermissions)
//        .flatMap(Collection::stream)
//        .filter(permission -> !permission.equals(Permission.CREATE_USER) && !permission.equals(Permission.LOGIN))
//        .collect(Collectors.toList());
//  }
//
//  private List<Permission> getRolePermissions(Role role) {
//    return permissionsDataService.getRolePermissions(role);
//  }
}
