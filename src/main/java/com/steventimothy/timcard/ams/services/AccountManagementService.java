package com.steventimothy.timcard.ams.services;

import com.steventimothy.timcard.repository.timcard.users.UsersDataService;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
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
   * The utility to validate user data.
   */
  private UserUtil userUtil;
  /**
   * The service that handles the data layer of the users data.
   */
  private UsersDataService usersDataService;

  /**
   * Creates a user.
   *
   * @param user The user wanted to be created.
   * @return The userId of the user that was created.
   */
  public UserId createUser(User user) {
    if (userUtil.validateUserCreation(user)) {
      return usersDataService.createUser(user);
    }
    else {
      throw new InvalidDataException("The user did not contain the correct data to be created.");
    }
  }
}
