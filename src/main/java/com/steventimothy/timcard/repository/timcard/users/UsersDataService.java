package com.steventimothy.timcard.repository.timcard.users;

import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.schemas.users.User;
import com.steventimothy.timcard.utils.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

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

  private UserMapper userMapper;
  private UsersDbService usersDbService;

  public void createUser(User user) {
    DataUser dataUser = userMapper.map(user);
    dataUser.salt(getSalt());
    dataUser.password(encryptPassword(dataUser.password(), dataUser.salt()));
    usersDbService.insert(dataUser);

  }

  private String getSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[64];
    random.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }

  private String encryptPassword(String password, String salt) {
    byte[] salter = Base64.getDecoder().decode(salt);

    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
      PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salter, 1000, 512);
      SecretKey key = skf.generateSecret(spec);
      byte[] res = key.getEncoded( );
      return Base64.getEncoder().encodeToString(res);
    }
    catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
      throw new UnsupportedOperationException("not supported.");
    }
  }













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
