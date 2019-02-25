package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.repository.schemas.DataRole;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.PermissionType;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.schemas.permissions.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * <h1>The PermissionMapper Class</h1>
 * <p>This class is responsible for mapping an a permission or role name to Enum.</p>
 */
@Slf4j
@Component
public class PermissionMapper {

  public DataPermission map(Permission permission) {

    if (permission != null) {
      DataPermission dataPermission = new DataPermission()
          .id(permission.id())
          .name(permission.permissionType().getName());
      dataPermission.date_created(map(permission.dateCreated()));
      dataPermission.last_modified(map(permission.lastModified()));

      return dataPermission;
    }
    else {
      return null;
    }
  }

  public Permission map(DataPermission dataPermission)
      throws UnsupportedOperationException {

    if (dataPermission != null) {
      Permission permission = new Permission()
          .id(dataPermission.id())
          .permissionType(mapPermissionType(dataPermission.name()));
      permission.dateCreated(map(dataPermission.date_created()));
      permission.lastModified(map(dataPermission.last_modified()));

      return permission;
    }
    else {
      return null;
    }
  }

  public DataRole map(Role role) {

    if (role != null) {
      DataRole dataRole = new DataRole()
          .id(role.id())
          .name(role.roleType().getName());
      dataRole.date_created(map(role.dateCreated()));
      dataRole.last_modified(map(role.lastModified()));

      return dataRole;
    }
    else {
      return null;
    }
  }

  public Role map(DataRole dataRole)
      throws UnsupportedOperationException {

    if (dataRole != null) {
      Role role = new Role()
          .id(dataRole.id())
          .roleType(mapRoletype(dataRole.name()));
      role.dateCreated(map(dataRole.date_created()));
      role.lastModified(map(dataRole.last_modified()));

      return role;
    }
    else {
      return null;
    }
  }

  private PermissionType mapPermissionType(String name)
      throws UnsupportedOperationException {

    if (name != null) {
      switch (name) {
        case "admin":
          return PermissionType.ADMIN;
        case "public":
          return PermissionType.PUBLIC;
        case "super-admin":
          return PermissionType.SUPER_ADMIN;
        case "user":
          return PermissionType.USER;
        default:
          throw new UnsupportedOperationException("The name: " + name + " could not be mapped to a permission type.");
      }
    }
    else {
      return null;
    }
  }

  private RoleType mapRoletype(String name)
      throws UnsupportedOperationException {

    if (name != null) {
      switch (name) {
        case "admin":
          return RoleType.ADMIN;
        case "general":
          return RoleType.GENERAL;
        case "super-admin":
          return RoleType.SUPER_ADMIN;
        case "user":
          return RoleType.USER;
        default:
          throw new UnsupportedOperationException("The name: " + name + " could not be mapped to a role type.");
      }
    }
    else {
      return null;
    }
  }

  private Timestamp map(Instant instant) {
    if (instant != null) {
      return Timestamp.from(instant);
    }
    else {
      return null;
    }
  }

  private Instant map(Timestamp timestamp) {
    if (timestamp != null) {
      return timestamp.toInstant();
    }
    else {
      return null;
    }
  }













//  /**
//   * Maps a data permission to a permission.
//   * @param dataPermission The data permission to map.
//   * @return The permission that the data permission mapped to.
//   * @throws UnsupportedOperationException Throws if the permission is unknown to the system.
//   */
//  public Permission map(DataPermission dataPermission)
//      throws UnsupportedOperationException {
//
//    if (dataPermission != null) {
//      switch (dataPermission.name()) {
//        case "admin":
//          return Permission.ADMIN;
//        case "public":
//          return Permission.PUBLIC;
//        case "super-admin":
//          return Permission.SUPER_ADMIN;
//        case "user":
//          return Permission.USER;
//        default:
//          throw new UnsupportedOperationException("Permission mapping not supported yet.");
//      }
//    }
//    else {
//      return null;
//    }
//  }
//
//  /**
//   * Maps a permission to a data permission.
//   * @param permission The permission to map.
//   * @return The data permission that the permission mapped to.
//   * @throws UnsupportedOperationException Throws if the permission is unknown to the system.
//   */
//  public DataPermission map(Permission permission)
//      throws UnsupportedOperationException {
//
//    if (permission != null) {
//      switch (permission) {
//        case ADMIN:
//        case PUBLIC:
//        case SUPER_ADMIN:
//        case USER:
//          return new DataPermission()
//              .name(permission.getValue());
//        default:
//          throw new UnsupportedOperationException("Permission mapping not supported yet.");
//      }
//    }
//    else {
//      return null;
//    }
//  }
}
