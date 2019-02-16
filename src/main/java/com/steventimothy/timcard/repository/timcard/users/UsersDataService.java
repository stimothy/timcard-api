package com.steventimothy.timcard.repository.timcard.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
















//  /**
//   * The utility that maps users to dataUsers and vice versa.
//   */
//  private UserMapper userMapper;
//  /**
//   * The database class responsible for making queries to the database.
//   */
//  private UsersDbService usersDbService;
////  /**
////   * The utility for mapping ids.
////   */
////  private IdMapper idMapper;
//
//  /**
//   * Creates a user in the database.
//   *
//   * @param user The user to create.
//   * @return The userId of the user created.
//   */
//  public UserId createUser(User user) {
//    DataUser dataUser = this.userMapper.map(user);
//    dataUser.date_created(Instant.now());
//    dataUser.last_modified(dataUser.date_created());
//
//    return new GeneralUserId(this.usersDbService.insert(dataUser));
//  }
//
////  /**
////   * Deletes a user in the database.
////   * @param userId The userId of the user to delete.
////   * @throws InvalidDataException Throws if the userId was not found in the database.
////   */
////  public void deleteUser(UserId userId) throws InvalidDataException {
////    this.usersDbService.delete(idMapper.mapUserIdToRawId(userId));
////  }
}
