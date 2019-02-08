package com.steventimothy.timcard.schemas.ids.sessions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>The SessionIdType Enum</h1>
 * <p>Holds the different types of sessionIds of the timcard api.</p>
 */
@Getter
@AllArgsConstructor
public enum SessionIdType {
  ADMIN("Admin"),
  USER("User"),
  GENERAL("General");

  /**
   * the value of the sessionId type.
   */
  private String value;
}
