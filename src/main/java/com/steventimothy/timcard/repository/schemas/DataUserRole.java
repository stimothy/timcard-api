package com.steventimothy.timcard.repository.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <h1>The DataUserRole Class</h1>
 * <p>This class holds the data members for the user_roles table database schema.</p>
 */
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataUserRole {

  /**
   * The id of the user to role.
   */
  private Long id;
  /**
   * The user id.
   */
  private String user_id;
  /**
   * The role id.
   */
  private Long role_id;
  /**
   * The time that this link was created.
   */
  private Instant date_created;
  /**
   * The time that this link was last modified.
   */
  private Instant last_modified;
}
