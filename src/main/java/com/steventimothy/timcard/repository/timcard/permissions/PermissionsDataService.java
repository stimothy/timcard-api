package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.repository.timcard.role_permissions.RolePermissionsDataService;
import com.steventimothy.timcard.repository.timcard.roles.RolesDataService;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.utils.mappers.PermissionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
   * The database layer to talk to the roles table.
   */
  private RolesDataService rolesDataService;
  /**
   * The database layer to talk to the role_permissions table.
   */
  private RolePermissionsDataService rolePermissionsDataService;
  /**
   * The utility for mapping permission names to values.
   */
  private PermissionMapper permissionMapper;

  public List<Permission> getPermissionsMatchingRoles(List<Role> roles) {
    return roles.stream()
        .map(role -> rolesDataService.getRoleId(role.getValue()))
        .filter(Objects::nonNull)
        .map(roleId -> rolePermissionsDataService.getPermissionsByRoleId(roleId))
        .flatMap(Collection::stream)
        .map(permissionId -> permissionsDbService.get(permissionId))
        .map(dataPermission -> permissionMapper.map(dataPermission.name()))
        .collect(Collectors.toList());
  }
}
