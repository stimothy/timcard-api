package com.steventimothy.timcard.schemas.permissions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>The Permission Enum</h1>
 * <p>Holds the different types of permissions of the timcard api.</p>
 */
@Getter
@AllArgsConstructor
public enum  PermissionType {

  ADMIN("admin"),
  PUBLIC("public"),
  SUPER_ADMIN("super-admin"),
  USER("user");

  /**
   * The name of the permission.
   */
  private String name;
}
