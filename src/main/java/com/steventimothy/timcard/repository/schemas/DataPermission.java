package com.steventimothy.timcard.repository.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * <h1>The DataPermission Class</h1>
 * <p>This class holds the data members for the permissions table database schema.</p>
 */
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataPermission {

  /**
   * The id of the permission.
   */
  private Long id;
  /**
   * The name of the permission.
   */
  private String name;
  /**
   * The date the permission was created.
   */
  private Timestamp date_created;
  /**
   * The date the permission was last updated.
   */
  private Timestamp last_modified;
}
