package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.ids.Id;
import com.steventimothy.timcard.schemas.ids.sessions.AdminSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.sessions.UserSessionId;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The IdMapper Class</h1>
 * <p>This class is responsible for mapping an encodedValue to Id.</p>
 */
@Slf4j
@Component
public class IdMapper {

  /**
   * Maps an encoded value to a session id.
   *
   * @param encodedValue The encoded value to map.
   * @return The session Id the encoded value mapped to.
   * @throws InvalidDataException Throws if it could not map the encoded value to sessionId.
   */
  public SessionId mapEncodedValueToSessionId(String encodedValue) throws InvalidDataException {
    return (SessionId) mapEncodedValueToId(encodedValue);
  }

  /**
   * Maps an encoded value to a user id.
   *
   * @param encodedValue The encoded value to map.
   * @return The user Id the encoded value mapped to.
   * @throws InvalidDataException Throws if it could not map the encoded value to userId.
   */
  public UserId mapEncodedValueToUserId(String encodedValue) throws InvalidDataException {
    return (UserId) mapEncodedValueToId(encodedValue);
  }

  /**
   * Maps an encoded value to an id.
   *
   * @param encodedValue The encoded value to map.
   * @return The Id the encoded value mapped to.
   * @throws InvalidDataException Throws if it could not map the encoded value to an Id.
   */
  public Id mapEncodedValueToId(String encodedValue) throws InvalidDataException {
    if (encodedValue != null) {
      String[] strs = encodedValue.split("\\.");

      if (strs.length == 3 && strs[0].equals("session")) {
        return mapDecodedRawIdToSessionId(strs[1], strs[2]);
      }
      else if (strs.length == 3 && strs[0].equals("user")) {
        return mapDecodedRawIdToUserId(strs[1], strs[2]);
      }
    }

    throw new InvalidDataException("Couldn't map encodedValue to Id");
  }

  /**
   * Maps an encoded value to a raw id.
   *
   * @param sessionId The encoded value to map.
   * @return The raw id of the session id.
   * @throws InvalidDataException Throws if it could not map the encoded value to a raw id.
   */
  public String mapSessionIdToRawId(SessionId sessionId) throws InvalidDataException {
    return mapIdToRawId(sessionId);
  }

  /**
   * Maps an encoded value to a raw id.
   *
   * @param userId The encoded value to map.
   * @return The raw id of the user id.
   * @throws InvalidDataException Throws if the encoded value could not be mapped.
   */
  public String mapUserIdToRawId(UserId userId) throws InvalidDataException {
    return mapIdToRawId(userId);
  }

  /**
   * Maps an encoded value to a raw id string.
   *
   * @param id The encoded value to map.
   * @return The raw id as a string of the encoded value.
   * @throws InvalidDataException throws if the encoded value cannot me mapped to an id.
   */
  public String mapIdToRawId(Id id) throws InvalidDataException {
    if (id != null && id.getEncodedValue() != null) {
      String[] strs = id.getEncodedValue().split("\\.");

      if (strs.length == 3) {
        return strs[2];
      }
    }

    throw new InvalidDataException("Couldn't map encodedValue to raw Id");
  }

  /**
   * Maps the decoded state of the sessionId to a sessionId.
   *
   * @param type  The type of sessionId.
   * @param rawId The rawId of the sessionId.
   * @return The sessionId that it mapped to.
   * @throws InvalidDataException Throws if the decoded values didn't map to any sessionId.
   */
  private SessionId mapDecodedRawIdToSessionId(String type, String rawId) throws InvalidDataException {
    switch (type) {
      case "admin":
        return new AdminSessionId(rawId);
      case "user":
        return new UserSessionId(rawId);
      case "general":
        return new GeneralSessionId(rawId);
      default:
        throw new InvalidDataException("No sessionId matches the type of the encoded value.");
    }
  }

  /**
   * Maps the decoded state of the userId to a userId.
   *
   * @param type  The type of userId.
   * @param rawId The value of the userId.
   * @return The userId that it mapped to.
   * @throws InvalidDataException Throws if the decoded values didn't map to any userId.
   */
  private UserId mapDecodedRawIdToUserId(String type, String rawId) throws InvalidDataException {
    switch (type) {
      case "admin":
        return new AdminUserId(rawId);
      case "general":
        return new GeneralUserId(rawId);
      default:
        throw new InvalidDataException("No userId matches the type of the encoded value.");
    }
  }
}
