package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.utils.UtilsBaseComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public abstract class MappersBaseComponent extends UtilsBaseComponent {

  /**
   * Maps encoded values to ids.
   */
  @Autowired
  protected IdMapper idMapper;
  /**
   * Maps exceptions to response entities.
   */
  @Autowired
  protected ExceptionMapper exceptionMapper;
  /**
   * Maps users to dataUsers and vice versa.
   */
  @Autowired
  protected UserMapper userMapper;

  /**
   * Creates a local dataUser.
   *
   * @return The dataUser created.
   */
  protected DataUser createLocalDataUser() {
    return createLocalDataUser(this.idMapper.mapUserIdToRawId(createLocalGeneralUserId()), "testUser4", "testUser4@test.com", "ch33t@sRunFaSt", Instant.now(), Instant.now());
  }

  /**
   * Creates a local dataUser.
   *
   * @return The dataUser created.
   */
  protected DataUser createAltLocalDataUser() {
    return createLocalDataUser(this.idMapper.mapUserIdToRawId(createAltLocalGeneralUserId()), "testUser5", "testUser5@test.com", "anTsW@lk!na1ine", Instant.now(), Instant.now());
  }

  /**
   * Creates a local dataUser.
   *
   * @param id            The id of the user.
   * @param username      The username of the user.
   * @param email         The password of the user.
   * @param enc_password  The password that will be encrypted.
   * @param date_created  The date created.
   * @param last_modified The date last modified.
   * @return The dataUser created.
   */
  protected DataUser createLocalDataUser(Long id, String username, String email, String enc_password, Instant date_created, Instant last_modified) {
    return new DataUser()
        .id(id)
        .username(username)
        .email(email)
        .enc_password(encryptPassword(enc_password))
        .date_created(date_created)
        .last_modified(last_modified);
  }

  /**
   * Maps an exception to a response entity.
   *
   * @param exception The exception to map.
   * @return The response entity this mapped to.
   */
  protected ResponseEntity mapExceptionToResponse(Exception exception) {
    return this.exceptionMapper.mapExceptionToResponse("myMethod", "myPath", "mySession", exception);
  }
}
