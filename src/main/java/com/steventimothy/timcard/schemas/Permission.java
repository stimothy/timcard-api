package com.steventimothy.timcard.schemas;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <h1>The User Class</h1>
 * <p>This class holds the data members associated with a user.</p>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class Permission {

  private Long id;
  private PermissionType permissionType;
  private Instant dateCreated;
  private Instant lastModified;
}
