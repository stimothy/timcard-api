package com.steventimothy.timcard.repository.timcard.users;

import com.steventimothy.timcard.schemas.exceptions.DatabaseConflictException;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersDataServiceTest extends UsersBaseComponent {

  /**
   * Tests that a user can be created.
   */
  @Test
  public void testCreateUser_Valid() {
    User user = createLocalUser();
    UserId userId = super.usersDataService.createUser(user);

    assertThat(userId)
        .isNotNull()
        .isEqualTo(user.userId());
  }

  /**
   * Tests that a user cannot be created for some reason.
   */
  @Test(expected = DatabaseConflictException.class)
  public void testCreateUser_Invalid() {
    User user = createLocalUser();

    UserId userId = super.usersDataService.createUser(user);
    super.usersDataService.createUser(user);
  }
}
