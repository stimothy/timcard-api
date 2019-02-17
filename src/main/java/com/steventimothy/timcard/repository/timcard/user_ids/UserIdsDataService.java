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

  private UserIdsDbService userIdsDbService;
  private IdMapper idMapper;

  public UserId getNewUserId() {
    DataUserId dataUserId = userIdsDbService.get();
    return new GeneralUserId(dataUserId.user_id());
  }

  public void freeUserId(UserId userId) {
    this.userIdsDbService.update(idMapper.mapUserIdToRawId(userId));
  }
}
