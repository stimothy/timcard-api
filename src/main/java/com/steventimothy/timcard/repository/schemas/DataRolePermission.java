package com.steventimothy.timcard.repository.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <h1>The DataRolePermission Class</h1>
 * <p>This class holds the data members for the role_permissions table database schema.</p>
 */
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataRolePermission {

  /**
   * The id of the role to permission.
   */
  private Long id;
  /**
   * The id of the role.
   */
  private Long role_id;
  /**
   * The id of the permission.
   */
  private Long permission_id;
  /**
   * The time the link between the role and permission was created.
   */
  private Instant date_created;
  /**
   * The time the link between the role and permission were last modified.
   */
  private Instant last_modified;
}
