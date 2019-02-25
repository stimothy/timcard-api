package com.steventimothy.timcard.repository.timcard.users;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.users.config.UsersDbConfig;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <h1>The UsersDbService Class</h1>
 * <p>This class is responsible for talking with the users table in the database.</p>
 */
@Slf4j
@Component
class UsersDbService extends TimcardDbService {

  /**
   * The config for the users table.
   */
  private UsersDbConfig dbConfig;

//  /**
//   * Inserts a user into the database.
//   * @param dataUser The data user to insert.
//   * @throws DatabaseDataException Throws if the data used in the query was bad.
//   */
//  DataUser insert(DataUser dataUser)
//      throws DatabaseDataException {
//
//    Connection connection = openConnection();
//
//    try {
//      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " (user_id, username, email, password, salt) VALUES(?, ?, ?, ?, ?)");
//      preparedStatement.setString(1, dataUser.user_id());
//      preparedStatement.setString(2, dataUser.username());
//      preparedStatement.setString(3, dataUser.email());
//      preparedStatement.setString(4, dataUser.password());
//      preparedStatement.setString(5, dataUser.salt());
//
//      //Execute the statement
//      preparedStatement.executeUpdate();
//    }
//    catch (SQLException ex) {
//      throw new DatabaseDataException("The user could not be inserted into the database.", ex);
//    }
//
//    //Close the connection.
//    closeConnection(connection);
//  }
//
//  DataUser get(Long id) {
//
//  }
//
//  DataUser get(String user_id) {
//
//  }
//
//  DataUser getByUsername(String username) {
//
//  }
//
//  DataUser getByEmail(String email) {
//
//  }
//
//  Boolean update(DataUser dataUser) {
//
//  }
//
//  Boolean delete(Long id) {
//
//  }
//
//  Boolean delete(String user_id) {
//
//  }
//
//  Boolean deleteByUsername(String username) {
//
//  }
//
//  Boolean deleteByEmail(String email) {
//
//  }


  /**
   * The constructor that receives the autowired components.
   *
   * @param dbConfig        The dbms config.
   * @param mysqlDataSource The datasource object for dbms.
   * @param timcardDbConfig The timcard db config.
   * @param usersDbConfig   The users table config.
   */
  @Autowired
  UsersDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, UsersDbConfig usersDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = usersDbConfig;
  }
}
