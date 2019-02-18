package com.steventimothy.timcard.schemas.ids.users;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <h1>The GeneralUserId Class</h1>
 * <p>This class holds the id for a general user.</p>
 */
@JsonTypeName("generalUserId")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GeneralUserId extends UserId {

  /**
   * Gets the encoded value of the id.
   *
   * @return The encoded userId.
   */
  @Override
  public String getEncodedValue() {
    return (super.rawId == null) ? null : "user.general." + super.rawId;
  }

  /**
   * Gets a printable friendly string of the class.
   *
   * @return A string representation of the object.
   */
  @Override
  public String toString() {
    return "GeneralUserId(id=" + getEncodedValue() + ")";
  }


  /**
   * The constructor for setting the raw id.
   *
   * @param rawId The raw id of the user.
   */
  public GeneralUserId(String rawId) {
    super(rawId);
  }
}
