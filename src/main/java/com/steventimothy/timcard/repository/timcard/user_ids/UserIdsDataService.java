package com.steventimothy.timcard.repository.timcard.user_ids;

import com.steventimothy.timcard.repository.schemas.DataUserId;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
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

//  /**
//   * The database layer used to talk to the user_ids table.
//   */
//  private UserIdsDbService userIdsDbService;
//  /**
//   * The utility that maps ids.
//   */
//  private IdMapper idMapper;
//
//  /**
//   * Gets a new user id from the database.
//   * @return The new user id.
//   * @throws DatabaseDataException Throws if the query was bad.
//   * @throws IllegalStateException Throws if a new user id couldn't be retrieved from the database.
//   */
//  public UserId getNewUserId()
//      throws DatabaseDataException, IllegalStateException {
//
//    DataUserId dataUserId = userIdsDbService.get();
//
//    if (dataUserId != null) {
//      return new GeneralUserId(dataUserId.user_id());
//    }
//    else {
//      throw new IllegalStateException("A new User id could not be retrieved.");
//    }
//  }
//
//  /**
//   * Frees up a user id in the database.
//   * @param userId The user id to free up.
//   * @throws InvalidDataException throws if the userId could not be mapped to a raw id.
//   * @throws DatabaseDataException throws if the data used in the query was bad.
//   */
//  public void freeUserId(UserId userId)
//      throws InvalidDataException, DatabaseDataException {
//
//    this.userIdsDbService.update(idMapper.mapUserIdToRawId(userId), false);
//  }
//
//  public void markUserIdUsed(UserId userId) {
//    DataUserId dataUserId = this.userIdsDbService.get(this.idMapper.mapUserIdToRawId(userId));
//    if (dataUserId == null) {
//      this.userIdsDbService.update(idMapper.mapUserIdToRawId(userId), true);
//    }
//    else {
//      throw new InvalidDataException("The user id has already been set by another user.");
//    }
//  }
}
