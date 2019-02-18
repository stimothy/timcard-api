package com.steventimothy.timcard.repository.timcard.user_ids;

import com.steventimothy.timcard.repository.schemas.DataUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.utils.mappers.IdMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserIdsDataService Class</h1>
 * <p>This class is the service between the system and the
 * user_ids table in the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserIdsDataService {

  /**
   * The database layer used to talk to the user_ids table.
   */
  private UserIdsDbService userIdsDbService;
  /**
   * The utility that maps ids.
   */
  private IdMapper idMapper;

  /**
   * Gets a new user id from the database.
   * @return The new user id.
   */
  public UserId getNewUserId() {
    DataUserId dataUserId = userIdsDbService.get();
    return new GeneralUserId(dataUserId.user_id());
  }

  /**
   * Frees up a user id in the database.
   * @param userId The user id to free up.
   */
  public void freeUserId(UserId userId) {
    this.userIdsDbService.update(idMapper.mapUserIdToRawId(userId));
  }
}
