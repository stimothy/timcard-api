package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.permissions.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The PermissionMapper Class</h1>
 * <p>This class is responsible for mapping an a permission or role name to Enum.</p>
 */
@Slf4j
@Component
public class PermissionMapper {

  /**
   * Maps a permission name to a Permission enum.
   * @param name The name of the permission.
   * @return The Permission matching the name.
   */
  public Permission map(String name) {
    switch (name) {
      case "admin":
        return Permission.ADMIN;
      case "create-admin-user":
        return Permission.CREATE_ADMIN_USER;
      case "create-user":
        return Permission.CREATE_USER;
      case "login":
        return Permission.LOGIN;
      case "super-admin":
        return Permission.SUPER_ADMIN;
      default:
        throw new UnsupportedOperationException("Permission mapping not supported yet.");
    }
  }
}
