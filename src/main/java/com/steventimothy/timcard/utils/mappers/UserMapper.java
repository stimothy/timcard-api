package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.ids.users.UserIdType;
import com.steventimothy.timcard.schemas.users.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserMapper Class</h1>
 * <p>This class maps users to dataUsers and vice versa.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserMapper {

  /**
   * Maps Ids to and from encoded and raw values.
   */
  private IdMapper idMapper;

  /**
   * Maps a user to a dataUser.
   *
   * @param user The user to map.
   * @return The dataUser that was mapped to.
   */
  public DataUser map(User user) {
    return new DataUser()
        .user_id(this.idMapper.mapUserIdToRawId(user.userId()))
        .username(user.username())
        .email(user.email())
        .password(user.password());
  }

  /**
   * Maps a dataUser to a user.
   *
   * @param dataUser The data user to map.
   * @param userType The type of user to map to.
   * @return The user that was mapped.
   * @throws UnsupportedOperationException Throws if the userType is not recognizable.
   */
  public User map(DataUser dataUser, UserIdType userType) throws UnsupportedOperationException {
    UserId userId;

    switch (userType) {
      case ADMIN:
        userId = new AdminUserId(dataUser.user_id());
        break;
      case GENERAL:
        userId = new GeneralUserId(dataUser.user_id());
        break;
      default:
        throw new UnsupportedOperationException("Unknown userType.");
    }

    return new User()
        .userId(userId)
        .username(dataUser.username())
        .email(dataUser.email())
        .password(dataUser.password());
  }
}
