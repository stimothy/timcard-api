package com.steventimothy.timcard.repository.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <h1>The DataSession Class</h1>
 * <p>This class holds the data members for the sessions table database schema.</p>
 */
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataSession {

  /**
   * The id of the session record.
   */
  private Long id;
  /**
   * The session id of the user.
   */
  private String session_id;
  /**
   * The user id of the session.
   */
  private String user_id;
  /**
   * The time the session should expire.
   */
  private Instant expiration;
  /**
   * The time the session was created.
   */
  private Instant date_created;
  /**
   * The time the session was last modified.
   */
  private Instant last_modified;
}
