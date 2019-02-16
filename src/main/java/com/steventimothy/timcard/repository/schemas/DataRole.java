package com.steventimothy.timcard.repository.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <h1>The DataRole Class</h1>
 * <p>This class holds the data members for the roles table database schema.</p>
 */
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataRole {

  /**
   * The id of the role.
   */
  private Long id;
  /**
   * The name of the role.
   */
  private String name;
  /**
   * The date the role was created.
   */
  private Instant date_created;
  /**
   * The date the role was last updated.
   */
  private Instant last_modified;
}
