package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.ids.Id;
import com.steventimothy.timcard.schemas.ids.sessions.*;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.ids.users.UserIdType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
   * @throws IllegalArgumentException Throws if it could not map the encoded value to sessionId.
   */
  public SessionId mapEncodedValueToSessionId(String encodedValue) throws IllegalArgumentException {
    return (SessionId) mapEncodedValueToId(encodedValue);
  }

  /**
   * Maps an encoded value to a user id.
   *
   * @param encodedValue The encoded value to map.
   * @return The user Id the encoded value mapped to.
   * @throws IllegalArgumentException Throws if it could not map the encoded value to userId.
   */
  public UserId mapEncodedValueToUserId(String encodedValue) throws IllegalArgumentException {
    return (UserId) mapEncodedValueToId(encodedValue);
  }

  /**
   * Maps an encoded value to an id.
   *
   * @param encodedValue The encoded value to map.
   * @return The Id the encoded value mapped to.
   * @throws IllegalArgumentException Throws if it could not map the encoded value to an Id.
   */
  public Id mapEncodedValueToId(String encodedValue) throws IllegalArgumentException {
    if (encodedValue != null) {
      String[] strs = encodedValue.split("\\.");

      if (strs.length == 3 && strs[0].equals("session")) {
        return mapDecodedValueToSessionId(strs[1], strs[2]);
      }
      else if (strs.length == 3 && strs[0].equals("user")) {
        return mapDecodedValueToUserId(strs[1], strs[2]);
      }
    }

    throw new IllegalArgumentException("Couldn't map encodedValue to Id");
  }

  /**
   * Maps an encoded value to a raw id.
   *
   * @param sessionId The encoded value to map.
   * @return The raw id of the session id.
   * @throws IllegalArgumentException Throws if it could not map the encoded value to a raw id.
   */
  public UUID mapSessionIdToRawId(SessionId sessionId) throws IllegalArgumentException {
    return UUID.fromString(mapIdToRawId(sessionId));
  }

  /**
   * Maps an encoded value to a raw id.
   *
   * @param userId The encoded value to map.
   * @return The raw id of the user id.
   * @throws IllegalArgumentException Throws if the encoded value could not be mapped.
   */
  public Long mapUserIdToRawId(UserId userId) throws IllegalArgumentException {
    return Long.parseLong(mapIdToRawId(userId));
  }

  /**
   * Maps an encoded value to a raw id string.
   *
   * @param id The encoded value to map.
   * @return The raw id as a string of the encoded value.
   * @throws IllegalArgumentException throws if the encoded value cannot me mapped to an id.
   */
  public String mapIdToRawId(Id id) throws IllegalArgumentException {
    if (id != null && id.getEncodedValue() != null) {
      String[] strs = id.getEncodedValue().split("\\.");

      if (strs.length == 3) {
        return strs[2];
      }
    }

    throw new IllegalArgumentException("Couldn't map encodedValue to raw Id");
  }

  /**
   * Maps a raw id to a sessionId.
   *
   * @param rawId         The raw id to map.
   * @param sessionIdType The sessionId type to map to.
   * @return The sessionId that was mapped.
   * @throws IllegalArgumentException      Throws if the raw Id cannot be converted to a UUID.
   * @throws UnsupportedOperationException Throws if the sessionIdType is unrecognizable.
   */
  public SessionId mapRawIdToSessionId(String rawId, SessionIdType sessionIdType) throws IllegalArgumentException, UnsupportedOperationException {
    if (rawId != null) {
      return mapRawIdToSessionId(UUID.fromString(rawId), sessionIdType);
    }
    else {
      throw new IllegalArgumentException("Raw Id cannot be null");
    }
  }

  /**
   * Maps a raw id to a sessionId.
   *
   * @param rawId         The raw id to map.
   * @param sessionIdType The sessionId type to map to.
   * @return The SessionId that was mapped.
   * @throws UnsupportedOperationException Throws if the sessionIdType is unrecognizable.
   */
  public SessionId mapRawIdToSessionId(UUID rawId, SessionIdType sessionIdType) throws IllegalArgumentException, UnsupportedOperationException {
    if (rawId != null) {
      switch (sessionIdType) {
        case ADMIN:
          return new AdminSessionId(rawId);
        case USER:
          return new UserSessionId(rawId);
        case GENERAL:
          return new GeneralSessionId(rawId);
        default:
          throw new UnsupportedOperationException("This class doesn't know how to map sessionIdType: " + sessionIdType.getValue());
      }
    }
    else {
      throw new IllegalArgumentException("RawId cannot be null");
    }
  }

  /**
   * Maps a raw id to a userId.
   *
   * @param rawId      The raw Id of the user.
   * @param userIdType The userId type of the user.
   * @return The userId that was mapped to.
   * @throws UnsupportedOperationException throws if the userId type is unknown.
   */
  public UserId mapRawIdToUserId(Long rawId, UserIdType userIdType) throws UnsupportedOperationException {
    if (rawId != null) {
      switch (userIdType) {
        case ADMIN:
          return new AdminUserId(rawId);
        case GENERAL:
          return new GeneralUserId(rawId);
        default:
          throw new UnsupportedOperationException("This class doesn't know how to map userIdType: " + userIdType.getValue());
      }
    }
    else {
      throw new IllegalArgumentException("raw Id cannot be null.");
    }
  }

  /**
   * Maps the decoded state of the sessionId to a sessionId.
   *
   * @param type  The type of sessionId.
   * @param value The value of the sessionId.
   * @return The sessionId that it mapped to.
   * @throws IllegalArgumentException Throws if the decoded values didn't map to any sessionId.
   */
  private SessionId mapDecodedValueToSessionId(String type, String value) throws IllegalArgumentException {
    switch (type) {
      case "admin":
        return new AdminSessionId(UUID.fromString(value));
      case "user":
        return new UserSessionId(UUID.fromString(value));
      case "general":
        return new GeneralSessionId(UUID.fromString(value));
      default:
        throw new IllegalArgumentException("No sessionId matches the type of the encoded value.");
    }
  }

  /**
   * Maps the decoded state of the userId to a userId.
   *
   * @param type  The type of userId.
   * @param value The value of the userId.
   * @return The userId that it mapped to.
   * @throws IllegalArgumentException Throws if the decoded values didn't map to any userId.
   */
  private UserId mapDecodedValueToUserId(String type, String value) throws IllegalArgumentException {
    switch (type) {
      case "admin":
        return new AdminUserId(Long.parseLong(value));
      case "general":
        return new GeneralUserId(Long.parseLong(value));
      default:
        throw new IllegalArgumentException("No userId matches the type of the encoded value.");
    }
  }
}
