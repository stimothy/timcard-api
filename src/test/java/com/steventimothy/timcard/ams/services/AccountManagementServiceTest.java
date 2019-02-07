package com.steventimothy.timcard.ams.services;

import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementServiceTest extends ServicesBaseComponent {

  /**
   * Tests that a user can be created.
   */
  @Test
  public void testCreateUser_Valid() {
    User user = createLocalUser();
    UserId userId = accountManagementService.createUser(user);

    assertThat(userId)
        .isNotNull()
        .isEqualTo(user.userId());
  }

  /**
   * Tests that the invalid data exception is thrown when the user cannot be created.
   */
  @Test(expected = InvalidDataException.class)
  public void testCreateUser_Invalid() {
    accountManagementService.createUser(null);
  }
}
