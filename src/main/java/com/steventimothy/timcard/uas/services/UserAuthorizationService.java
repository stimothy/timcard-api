package com.steventimothy.timcard.uas.services;

import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <h1>The UserAuthorizationService Class</h1>
 * <p>This class holds the logic for the UAS system.</p>
 */
@Slf4j
@Component
public class UserAuthorizationService {

  /**
   * Creates a general session id.
   * @return The general session id created.
   */
  public GeneralSessionId createGeneralSessionId() {
    return new GeneralSessionId(UUID.randomUUID().toString());
  }






















//  public UserId getUserIdFromSessionId(SessionId sessionId) {
//    return null;
//  }
}
