package com.steventimothy.timcard.repository.timcard.sessions;

import com.steventimothy.timcard.repository.schemas.DataSession;
import com.steventimothy.timcard.repository.timcard.permissions.PermissionsDataService;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.utils.mappers.IdMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The SessionsDataService Class</h1>
 * <p>This class is the service that connects the system
 * with the sessions table in the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class SessionsDataService {

  /**
   * The sessions database service layer.
   */
  private SessionsDbService sessionsDbService;
  /**
   * the permissions database service layer.
   */
  private PermissionsDataService permissionsDataService;
  /**
   * The mapper used to map between encoded values, raw values, and class values.
   */
  private IdMapper idMapper;

  /**
   * Gets the user id from the session.
   *
   * @param sessionId The session id to abstract the user id from.
   * @return The user Id matching the session id given.
   * @throws InvalidDataException throws if the session could not be found in the database.
   * @throws DatabaseDataException throws if there was a problem with the data used to query.
   */
  public UserId getUserId(SessionId sessionId)
      throws InvalidDataException, DatabaseDataException {

    String session = idMapper.mapSessionIdToRawId(sessionId);

    DataSession dataSession = sessionsDbService.get(session);

    if (dataSession != null) {
      UserId userId = new GeneralUserId(dataSession.user_id());
      if (permissionsDataService.getUserPermissions(userId).contains(Permission.ADMIN)) {
        userId = new AdminUserId(dataSession.user_id());
      }

      return userId;
    }
    else {
      throw new InvalidDataException("The user is unknown to the system.");
    }
  }
}
