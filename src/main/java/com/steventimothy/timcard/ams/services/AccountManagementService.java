package com.steventimothy.timcard.ams.services;

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

//  /**
//   * The data service layer for the users database table.
//   */
//  private UsersDataService usersDataService;
//  /**
//   * The client used to talk to the UAS system.
//   */
//  private UasClient uasClient;
//  /**
//   * The client used to talk to the PMS system.
//   */
//  private PmsClient pmsClient;
//  /**
//   * The utility to verify the user has valid data.
//   */
//  private UserUtil userUtil;
//  /**
//   * the utility that maps ids.
//   */
//  private IdMapper idMapper;
//
//  /**
//   * Creates a user of the given type in the database.
//   *
//   * @param user       The user to create.
//   * @param userIdType The type of user to create.
//   * @return The userId of the user created.
//   * @throws InvalidDataException Throws if the user doesn't have valid data.
//   * @throws DatabaseDataException Throws if the data used in the query was bad.
//   * @throws IllegalStateException Throws if the password couldn't be hashed.
//   */
//  public UserId createUser(User user, UserIdType userIdType)
//      throws InvalidDataException, DatabaseDataException, IllegalStateException {
//
////    userUtil.validateUserCreation(user);
////    if (user.userId() == null || user.userId().getEncodedValue() == null) {
////      user.userId(this.uasClient.getNewUserId());
////    }
////    else {
////      this.uasClient.markUserIdUsed(user.userId());
////    }
////
////    try {
////      this.usersDataService.createUser(user);
////
////      if (UserIdType.ADMIN.equals(userIdType)) {
////        user.userId(new AdminUserId(idMapper.mapUserIdToRawId(user.userId())));
////        this.pmsClient.addRole(user.userId(), Role.ADMIN);
////      }
////      else {
////        this.pmsClient.addRole(user.userId(), Role.USER);
////      }
////    }
////    catch (Exception ex) {
////      this.uasClient.freeUserId(user.userId());
////      throw ex;
////    }
//
//    return user.userId();
//  }
}
