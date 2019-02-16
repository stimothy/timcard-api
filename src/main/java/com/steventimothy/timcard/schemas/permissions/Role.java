package com.steventimothy.timcard.schemas.permissions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>The Role Enum</h1>
 * <p>Holds the different types of roles of the timcard api.</p>
 */
@Getter
@AllArgsConstructor
public enum Role {

  ADMIN("admin"),
  GENERAL("general"),
  SUPER_ADMIN("super-admin"),
  USER("user");

  /**
   * The value of a role.
   */
  private String value;
}
