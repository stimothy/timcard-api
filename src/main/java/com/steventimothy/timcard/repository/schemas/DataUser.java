package com.steventimothy.timcard.repository.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <h1>The DataUser Class</h1>
 * <p>This class holds the data members for the users table database schema.</p>
 */
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataUser {

  /**
   * The id of the user.
   */
  private Long id;
  /**
   * The username of the user.
   */
  private String username;
  /**
   * The email for the user.
   */
  private String email;
  /**
   * The encrypted password of the user.
   */
  private String enc_password;
  /**
   * The date the user was created.
   */
  private Instant date_created;
  /**
   * The date the user was last modified.
   */
  private Instant last_modified;
}
