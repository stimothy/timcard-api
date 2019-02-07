package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.utils.mappers.IdMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h1>The IdentityUtil Class</h1>
 * <p>This class validates that an encoded sessionId is a valid session id
 * and has permission..</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class IdentityUtil {

  /**
   * The mapper for mapping encoded values to ids.
   */
  private IdMapper idMapper;

  /**
   * Validates that the encoded Session id is a valid session Id and that that
   * session Id has the given permission.
   *
   * @param encodedValue The encoded id of the session.
   * @param permissions  The permissions that user must have.
   * @return The session Id of the user if all was validated.
   * @throws UnauthorizedException Throws if the encoded value cannot be mapped to a sessionId.
   * @throws ForbiddenException    Throws if the session Id does not have the right permissions.
   */
  public SessionId validate(String encodedValue, List<Permission> permissions) throws UnauthorizedException, ForbiddenException {

    try {
      SessionId sessionId = this.idMapper.mapEncodedValueToSessionId(encodedValue);

      if (permissions.size() == 1 && permissions.get(0).equals(Permission.CREATE_USER)) {
        return sessionId;
      }
      else {
        throw new ForbiddenException("Permission Denied");
      }
    }
    catch (IllegalArgumentException ex) {
      throw new UnauthorizedException("Unknown Id");
    }
  }
}