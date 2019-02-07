package com.steventimothy.timcard.schemas.ids;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;

/**
 * <h1>The Id Interface</h1>
 * <p>Holds the functionality of an id.</p>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = UserId.class, name = "userId"),
    @JsonSubTypes.Type(value = SessionId.class, name = "sessionId")
})
public interface Id {

  /**
   * Gets the encoded string of the id.
   *
   * @return The encoded string of the raw id.
   */
  default String getEncodedValue() {
    throw new UnsupportedOperationException("getEncodedValue has not been implemented for this class.");
  }
}
