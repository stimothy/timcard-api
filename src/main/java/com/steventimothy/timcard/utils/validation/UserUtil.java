package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
import com.steventimothy.timcard.utils.mappers.IdMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserUtil Class</h1>
 * <p>This class is responsible for validating the data of a user.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserUtil {

  /**
   * Maps encoded ids to raw ids.
   */
  private IdMapper idMapper;
  /**
   * The regex to check emails.
   */
  private final String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

  /**
   * Validates that the user on creation has the correct information.
   *
   * @param user The user to create.
   * @return True if all information was valid, false otherwise.
   */
  public boolean validateUserCreation(User user) {
    return (user != null && hasValidCreationData(user));
  }

  /**
   * Validates that the data in the user on creation has valid data.
   *
   * @param user The user to create.
   * @return True if the data was valid, false otherwise.
   */
  private boolean hasValidCreationData(User user) {
    return (hasValidCreationUserId(user.userId()) && hasValidUsername(user.username()) &&
        hasValidEmail(user.email()) && hasValidPassword(user.password()));
  }

  /**
   * Validates that the id on creation is valid.
   *
   * @param userId The userId given for creation.
   * @return True if the id was valid, false otherwise.
   */
  private boolean hasValidCreationUserId(UserId userId) {
    try {
      return (userId == null || (userId != null &&
          (userId.getEncodedValue() == null ||
              hasValidTestId(idMapper.mapUserIdToRawId(userId)))));
    }
    catch (InvalidDataException ex) {
      return false;
    }
  }

  /**
   * Validates that there is a valid test id.
   *
   * @param rawId The raw Id of the userId.
   * @return true if it is a valid test id, false otherwise.
   */
  private boolean hasValidTestId(String rawId) {
    return (rawId.charAt(0) == 'T');
  }

  /**
   * Validates that the username has correct data.
   *
   * @param username The username to validate.
   * @return True if the username is valid, false otherwise.
   */
  private boolean hasValidUsername(String username) {
    return (username != null && username.length() > 4 && username.length() < 51);
  }

  /**
   * Validates that the email is valid.
   *
   * @param email the email to validate.
   * @return True if the email was valid, false otherwise.
   */
  private boolean hasValidEmail(String email) {
    return (email != null && email.length() < 101 && email.matches(EMAIL_REGEX));
  }

  /**
   * Validates that the password is valid.
   *
   * @param password validates that the password is correct.
   * @return true if it is a valid password, false otherwise.
   */
  private boolean hasValidPassword(String password) {
    return (password != null && password.length() == 64);
  }
}
