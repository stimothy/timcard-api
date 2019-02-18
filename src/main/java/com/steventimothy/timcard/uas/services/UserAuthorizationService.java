package com.steventimothy.timcard.uas.services;

import com.steventimothy.timcard.repository.timcard.user_ids.UserIdsDataService;
import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <h1>The UserAuthorizationService Class</h1>
 * <p>This class holds the logic for the UAS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserAuthorizationService {

  private UserIdsDataService userIdsDataService;

  /**
   * Creates a general session id.
   *
   * @return The general session id created.
   */
  public GeneralSessionId createGeneralSessionId() {
    return new GeneralSessionId(UUID.randomUUID().toString());
  }

  public UserId getNewUserId() {
    return userIdsDataService.getNewUserId();
  }

  public void freeUserId(UserId userId) {
    this.userIdsDataService.freeUserId(userId);
  }
}
