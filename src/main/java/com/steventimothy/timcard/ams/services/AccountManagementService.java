package com.steventimothy.timcard.ams.services;

import com.steventimothy.timcard.clients.PmsClient;
import com.steventimothy.timcard.clients.UasClient;
import com.steventimothy.timcard.repository.timcard.users.UsersDataService;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.ids.users.UserIdType;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.schemas.users.User;
import com.steventimothy.timcard.utils.mappers.IdMapper;
import com.steventimothy.timcard.utils.validation.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The AccountManagementService Class</h1>
 * <p>This class holds the logic for the AMS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AccountManagementService {

  /**
   * The client used to talk to the UAS system.
   */
  private UasClient uasClient;
  private PmsClient pmsClient;
  /**
   * The utility to verify the user has valid data.
   */
  private UserUtil userUtil;
  private UsersDataService usersDataService;
  private IdMapper idMapper;

  /**
   * Creates a user of the given type in the database.
   * @param user The user to create.
   * @param userIdType The type of user to create.
   * @return The userId of the user created.
   */
  public UserId createUser(User user, UserIdType userIdType) {
    userUtil.validateUserCreation(user);
    if (user.userId() == null) {
      user.userId(uasClient.getNewUserId());
    }
    try {
      usersDataService.createUser(user);

      if (UserIdType.ADMIN.equals(userIdType)) {
        user.userId(new AdminUserId(idMapper.mapUserIdToRawId(user.userId())));
        pmsClient.addRole(user.userId(), Role.ADMIN);
      }
      else {
        pmsClient.addRole(user.userId(), Role.USER);
      }
    }
    catch (Exception ex) {
      uasClient.freeUserId(user.userId());
    }

    return user.userId();
  }















//  /**
//   * The utility to validate user data.
//   */
//  private UserUtil userUtil;
//  /**
//   * The service that handles the data layer of the users data.
//   */
//  private UsersDataService usersDataService;
//
//  /**
//   * Creates a user.
//   *
//   * @param user The user wanted to be created.
//   * @return The userId of the user that was created.
//   * @throws InvalidDataException throws if the user data is invalid.
//   * @throws DatabaseConflictException throws if there was a conflict in the database.
//   */
//  public UserId createUser(User user) throws InvalidDataException {
//    //if (this.userUtil.validateUserCreation(user)) {
//      user.userId(new GeneralUserId());
//      return null;// this.usersDataService.createUser(user);
////    }
////    else {
////      throw new InvalidDataException("The user did not contain the correct data to be created.");
////    }
//  }
////
////  /**
////   * Deletes a user.
////   * @param userId The userId of the user to delete.
////   * @param sessionId The sessionId of the user.
////   * @throws InvalidDataException Throws if the user could not be deleted in the database.
////   */
////  public void deleteUser(UserId userId, SessionId sessionId) throws InvalidDataException {
////    this.usersDataService.deleteUser(userId);
////    //this.uasClient.logout(sessionId);
////  }
}
