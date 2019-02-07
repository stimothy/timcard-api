package com.steventimothy.timcard.schemas.ids.sessions;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * <h1>The GeneralSessionId Class</h1>
 * <p>This class holds the id for a general Session.</p>
 */
@JsonTypeName("generalSessionId")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GeneralSessionId extends SessionId {

  /**
   * Gets the encoded value of the id.
   *
   * @return The encoded sessionId.
   */
  @Override
  public String getEncodedValue() {
    return (super.rawId == null) ? null : "session.general." + super.rawId;
  }

  /**
   * Gets a printable friendly string of the class.
   *
   * @return A string representation of the object.
   */
  @Override
  public String toString() {
    return "GeneralSessionId(id=" + getEncodedValue() + ")";
  }


  /**
   * The constructor for setting the raw id.
   * @param rawId The raw id of the session.
   */
  public GeneralSessionId(UUID rawId) {
    super(rawId);
  }
}
