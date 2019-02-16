package com.steventimothy.timcard.repository.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <h1>The DataUserId Class</h1>
 * <p>This class holds the data members for the user_ids table database schema.</p>
 */
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataUserId {

  /**
   * The id of the record.
   */
  private Long id;
  /**
   * The user id.
   */
  private String user_id;
  /**
   * If the user id is being used.
   */
  private Boolean used;
}
