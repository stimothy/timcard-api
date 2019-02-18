package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.users.User;
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
   * The regex to check emails.
   */
  private static final String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

  /**
   * Validates that the user on creation has the correct information.
   *
   * @param user The user to create.
   */
  public void validateUserCreation(User user) {
    if (user != null) {
      validateCreationData(user);
    }
    else {
      throw new InvalidDataException("User cannot be null.");
    }
  }

  /**
   * Validates that the data in the user on creation has valid data.
   *
   * @param user The user to create.
   */
  private void validateCreationData(User user) {
    validateUsername(user.username());
    validateEmail(user.email());
    validatePassword(user.password());
  }

  /**
   * Validates that the username has correct data.
   *
   * @param username The username to validate.
   */
  private void validateUsername(String username) {
    if (username == null || username.length() < 5 || username.length() > 50) {
      throw new InvalidDataException("Username cannot be null and must be between 5 and 50 characters long.");
    }
  }

  /**
   * Validates that the email is valid.
   *
   * @param email the email to validate.
   */
  private void validateEmail(String email) {
    if (email == null || email.length() > 100 || !email.matches(EMAIL_REGEX)) {
      throw new InvalidDataException("Email cannot be null and must be less than 100 or less characters long.");
    }
  }

  /**
   * Validates that the password is valid.
   *
   * @param password validates that the password is correct.
   */
  private void validatePassword(String password) {
    if (password == null || password.length() != 88) {
      throw new InvalidDataException("Password cannot be null and must be 88 characters long.");
    }
  }
}
