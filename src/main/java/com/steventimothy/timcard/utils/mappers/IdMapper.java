package com.steventimothy.timcard.utils.mappers;

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
