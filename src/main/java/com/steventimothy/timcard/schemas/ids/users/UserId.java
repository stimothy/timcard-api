package com.steventimothy.timcard.schemas.ids.users;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.steventimothy.timcard.schemas.ids.Id;
import lombok.NoArgsConstructor;

/**
 * <h1>The UserId Class</h1>
 * <p>This class holds the id for a user.</p>
 */
@JsonTypeName("userId")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = GeneralUserId.class, name = "generalUserId"),
    @JsonSubTypes.Type(value = AdminUserId.class, name = "adminUserId")
})
@NoArgsConstructor
public abstract class UserId implements Id {

  /**
   * The raw id of a user.
   */
  protected String rawId;


  /**
   * The constructor that sets the userId.
   *
   * @param rawId The id of the user.
   */
  protected UserId(String rawId) {
    this.rawId = rawId;
  }
}
