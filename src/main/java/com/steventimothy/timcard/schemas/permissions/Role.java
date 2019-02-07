package com.steventimothy.timcard.schemas.permissions;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>The Role Enum</h1>
 * <p>Holds the different types of roles of the timcard api.</p>
 */
@AllArgsConstructor
public enum Role {

  GENERAL(
      Collections.emptyList(),
      Collections.singletonList(
          Permission.CREATE_USER
      )
  ),
  SUPER_ADMIN(
      Collections.singletonList(
          Role.GENERAL
      ),
      Collections.singletonList(
          Permission.SUPER_ADMIN
      )
  );

  /**
   * The list of roles this role has.
   */
  private List<Role> roles;
  /**
   * The list of permissions that this role has.
   */
  private List<Permission> permissions;

  /**
   * Gets the roles associated with this role.
   *
   * @return The roles associated with this role.
   */
  public List<Role> getRoles() {
    return this.roles;
  }

  /**
   * Gets all permissions associated with this role.
   *
   * @return The permissions associated with this role.
   */
  public List<Permission> getPermissions() {
    List<Permission> permissionList = this.roles.stream()
        .map(Role::getPermissions)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    permissionList.addAll(this.permissions);

    return permissionList.stream()
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * Checks to see if a role has a specific permission.
   *
   * @param permission The permission of the role.
   * @return True if the role has the permission, false otherwise.
   */
  public Boolean hasPermission(Permission permission) {
    return getPermissions().contains(permission);
  }
}
