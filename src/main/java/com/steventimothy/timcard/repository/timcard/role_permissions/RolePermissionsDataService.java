package com.steventimothy.timcard.repository.timcard.role_permissions;

import com.steventimothy.timcard.repository.schemas.DataRolePermission;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>The RolePermissionsDataService Class</h1>
 * <p>This class is the data service class between the system
 * and the role_permissions database layer.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class RolePermissionsDataService {

  /**
   * The service layer used to talk to the role_permissions table.
   */
  private RolePermissionsDbService rolePermissionsDbService;

  /**
   * Gets the permissions that are linked to the role id.
   * @param roleId The id of the role.
   * @return The permissions that are linked to the role.
   */
  public List<Long> getPermissionsByRoleId(Long roleId) {
    return rolePermissionsDbService.getAllByRoleId(roleId).stream()
        .map(DataRolePermission::permission_id)
        .collect(Collectors.toList());
  }
}
