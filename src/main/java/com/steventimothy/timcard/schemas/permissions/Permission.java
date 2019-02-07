package com.steventimothy.timcard.schemas.permissions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>The Permission Enum</h1>
 * <p>Holds the different types of permissions of the timcard api.</p>
 */
@Getter
@AllArgsConstructor
public enum Permission {

  SUPER_ADMIN("super-admin"),
  CREATE_USER("create-user");

  /**
   * The value of the permission.
   */
  private String value;
}
