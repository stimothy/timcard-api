package com.steventimothy.timcard.repository.timcard.users;

import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
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

//  /**
//   * The utility that maps users and data users.
//   */
//  private UserMapper userMapper;
//  /**
//   * That database layer that is used to talk to the users table.
//   */
//  private UsersDbService usersDbService;
//
//  /**
//   * Creates a user in the data.
//   * @param user The user to create.
//   * @throws DatabaseDataException throws if there was a problem with the data in the query.
//   * @throws IllegalStateException throws if the password couldn't be hashed.
//   */
//  public void createUser(User user)
//      throws DatabaseDataException, IllegalStateException {
//
//    DataUser dataUser = userMapper.map(user);
//    dataUser.salt(getSalt());
//    dataUser.password(encryptPassword(dataUser.password(), dataUser.salt()));
//    usersDbService.insert(dataUser);
//  }
//
//  /**
//   * Gets a salt used to hash the password.
//   * @return The salt used to hash the password.
//   */
//  private String getSalt() {
//    SecureRandom random = new SecureRandom();
//    byte[] salt = new byte[64];
//    random.nextBytes(salt);
//    return Base64.getEncoder().encodeToString(salt);
//  }
//
//  /**
//   * Hashes the password to store in the database.
//   * @param password The password to hash.
//   * @param salt The salt used to hash the password.
//   * @return The hashed password.
//   * @throws IllegalStateException Throws if the password couldn't be hashed.
//   */
//  private String encryptPassword(String password, String salt)
//      throws IllegalStateException {
//
//    byte[] salter = Base64.getDecoder().decode(salt);
//
//    try {
//      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
//      PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salter, 1000, 512);
//      SecretKey key = skf.generateSecret(spec);
//      byte[] res = key.getEncoded();
//      return Base64.getEncoder().encodeToString(res);
//    }
//    catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//      throw new IllegalStateException("not supported.");
//    }
//  }
}
