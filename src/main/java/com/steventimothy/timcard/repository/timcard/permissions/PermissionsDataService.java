package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.repository.timcard.role_permissions.RolePermissionsDataService;
import com.steventimothy.timcard.repository.timcard.user_roles.UserRolesDataService;
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
  private UserRolesDataService userRolesDataService;
  private RolePermissionsDataService rolePermissionsDataService;
  private PermissionMapper permissionMapper;

  public List<Permission> getUserPermissions(UserId userId) {
    List<Long> roleIds = userRolesDataService.getRoleIds(userId);

    return roleIds.stream()
        .map(roleId -> rolePermissionsDataService.getPermissionIds(roleId))
        .flatMap(Collection::stream)
        .map(permissionId -> permissionMapper.map(permissionsDbService.get(permissionId)))
        .collect(Collectors.toList());
  }
}
