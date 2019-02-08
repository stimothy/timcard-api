package com.steventimothy.timcard.schemas.ids.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>The UserIdType Enum</h1>
 * <p>Holds the different types of userIds of the timcard api.</p>
 */
@Getter
@AllArgsConstructor
public enum UserIdType {
  ADMIN("Admin"),
  GENERAL("General");

  /**
   * The value of the userId type.
   */
  private String value;
}
