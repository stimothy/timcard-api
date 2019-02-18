package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.repository.timcard.role_permissions.RolePermissionsDataService;
import com.steventimothy.timcard.repository.timcard.user_roles.UserRolesDataService;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.utils.mappers.PermissionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>The PermissionsDataService Class</h1>
 * <p>This class is the data service layer between the system and
 * the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class PermissionsDataService {

  /**
   * The database layer to talk to the permissions table.
   */
  private PermissionsDbService permissionsDbService;
  /**
   * The data service layer to talk to the user_roles data table.
   */
  private UserRolesDataService userRolesDataService;
  /**
   * The data service layer to talk to the role_permissions data table.
   */
  private RolePermissionsDataService rolePermissionsDataService;
  /**
   * Utility to map permissions and names.
   */
  private PermissionMapper permissionMapper;

  /**
   * Gets a list of permissions that a user has.
   * @param userId The user id to get permissions.
   * @return The list of permissions the user has.
   * @throws InvalidDataException throws if the user id was not valid.
   * @throws DatabaseDataException throws if the data used to query the database was bad.
   */
  public List<Permission> getUserPermissions(UserId userId)
      throws InvalidDataException, DatabaseDataException {

    List<Long> roleIds = userRolesDataService.getRoleIds(userId);

    return roleIds.stream()
        .map(roleId -> rolePermissionsDataService.getPermissionIds(roleId))
        .flatMap(Collection::stream)
        .map(permissionId -> permissionMapper.map(permissionsDbService.get(permissionId)))
        .collect(Collectors.toList());
  }
}
