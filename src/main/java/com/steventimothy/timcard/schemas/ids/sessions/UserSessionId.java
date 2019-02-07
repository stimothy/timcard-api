package com.steventimothy.timcard.schemas.ids.sessions;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * <h1>The UserSessionId Class</h1>
 * <p>This class holds the id for a user Session.</p>
 */
@JsonTypeName("userSessionId")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserSessionId extends SessionId {

  /**
   * Gets the encoded value of the id.
   *
   * @return The encoded sessionId.
   */
  @Override
  public String getEncodedValue() {
    return (super.rawId == null) ? null : "session.user." + super.rawId;
  }

  /**
   * Gets a printable friendly string of the class.
   *
   * @return A string representation of the object.
   */
  @Override
  public String toString() {
    return "UserSessionId(id=" + getEncodedValue() + ")";
  }


  /**
   * The constructor for setting the raw id.
   * @param rawId The raw id of the session.
   */
  public UserSessionId(UUID rawId) {
    super(rawId);
  }
}
