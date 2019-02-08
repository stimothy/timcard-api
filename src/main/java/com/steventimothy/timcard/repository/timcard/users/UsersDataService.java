package com.steventimothy.timcard.repository.timcard.users;

import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.schemas.exceptions.DatabaseConflictException;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
import com.steventimothy.timcard.utils.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * <h1>The UsersDataService Class</h1>
 * <p>This classes responsibility is the connection between the application and the
 * database class. It sets up the data to be queried in the database for the
 * users table.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UsersDataService {

  /**
   * The utility that maps users to dataUsers and vice versa.
   */
  private UserMapper userMapper;
  /**
   * The database class responsible for making queries to the database.
   */
  private UsersDbService usersDbService;

  /**
   * Creates a user in the database.
   *
   * @param user The user to create.
   * @return The userId of the user created.
   */
  public UserId createUser(User user) throws DatabaseConflictException {
    DataUser dataUser = this.userMapper.map(user);
    dataUser.date_created(Instant.now());
    dataUser.last_modified(dataUser.date_created());

    return new GeneralUserId(this.usersDbService.insert(dataUser));
  }
}
