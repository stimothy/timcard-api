package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.ids.Id;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.utils.UtilsBaseComponent;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MappersBaseComponent extends UtilsBaseComponent {

  /**
   * The utility used to map ids.
   */
  @Autowired
  private IdMapper idMapper;

  /**
   * Creates a local general user id.
   * @return The user id created.
   */
  protected GeneralUserId createLocalGeneralUserId() {
    return createLocalGeneralUserId(createRandomRawUserId());
  }

  /**
   * Creates a local general user id.
   * @param rawId the raw id of the user id.
   * @return The user id created.
   */
  protected GeneralUserId createLocalGeneralUserId(String rawId) {
    return new GeneralUserId(rawId);
  }

  /**
   * Maps an encoded value to a session id.
   * @param encodedValue The encoded value to map.
   * @return The session id mapped to.
   */
  protected SessionId mapEncodedValueToSessionId(String encodedValue) {
    return this.idMapper.mapEncodedValueToSessionId(encodedValue);
  }

  /**
   * Maps an encoded value to a user id.
   * @param encodedValue the encoded value to map.
   * @return The user id mapped to.
   */
  protected UserId mapEncodedValueToUserId(String encodedValue) {
    return this.idMapper.mapEncodedValueToUserId(encodedValue);
  }

  /**
   * Maps an encoded value to id.
   * @param encodedValue The encoded value to map.
   * @return The id mapped to.
   */
  protected Id mapEncodedValueToId(String encodedValue) {
    return this.idMapper.mapEncodedValueToId(encodedValue);
  }

  /**
   * Maps a session id to a raw id.
   * @param sessionId The session id to map.
   * @return The raw id of the session id.
   */
  protected String mapSessionIdToRawId(SessionId sessionId) {
    return this.idMapper.mapSessionIdToRawId(sessionId);
  }

  /**
   * Maps a user id to a raw id.
   * @param userId The user id to map.
   * @return The raw id of the user id.
   */
  protected String mapUserIdToRawId(UserId userId) {
    return this.idMapper.mapUserIdToRawId(userId);
  }

  /**
   * Maps an id to a raw id.
   * @param id The id to map.
   * @return The raw id of the id.
   */
  protected String mapIdToRawId(Id id) {
    return this.idMapper.mapIdToRawId(id);
  }
}
