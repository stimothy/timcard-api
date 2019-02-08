package com.steventimothy.timcard.repository.timcard.users;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.users.config.UsersDbConfig;
import com.steventimothy.timcard.schemas.exceptions.DatabaseConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

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

  /**
   * Inserts a user into the users table.
   *
   * @param dataUser the user to insert.
   * @return The id of the user after inserting.
   */
  Long insert(DataUser dataUser) {
    Long id = null;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " VALUES(?, ?, ?, ?, ?, ?)");
      if (dataUser.id() == null) {
        preparedStatement.setNull(1, Types.BIGINT);
      }
      else {
        preparedStatement.setLong(1, dataUser.id());
      }
      preparedStatement.setString(2, dataUser.username());
      preparedStatement.setString(3, dataUser.email());
      preparedStatement.setString(4, dataUser.enc_password());
      preparedStatement.setTimestamp(5, Timestamp.from(dataUser.date_created()));
      preparedStatement.setTimestamp(6, Timestamp.from(dataUser.date_created()));

      //Execute the statement
      preparedStatement.executeUpdate();

      id = getIdByCredentials(dataUser.username(), dataUser.enc_password(), connection);
    }
    catch (SQLException ex) {
      throw new DatabaseConflictException("Could not insert the user into the database. " + ex.getMessage());
    }

    //Close the connection.
    closeConnection(connection);

    return id;
  }

  /**
   * Gets a dataUser by username and password helper method.
   *
   * @param username     the username of the user to retrieve.
   * @param enc_password the password of the user to retrieve.
   * @param connection   The connection to the database.
   * @return The id retrieved from the database or null if it couldn't.
   * @throws SQLException Throws if there was an sql exception on retrieving the data.
   */
  private Long getIdByCredentials(String username, String enc_password, Connection connection) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.id FROM " + dbConfig.getTableName() + " u WHERE u.username = ? AND u.enc_password = ?");
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, enc_password);

    //Execute the statement
    ResultSet resultSet = preparedStatement.executeQuery();

    return getIdFromResultSet(resultSet);
  }

  /**
   * Get the id from a ResultSet.
   *
   * @param resultSet the resultSet to get the id from.
   * @return The id from the resultSet, null if it doesn't exist.
   * @throws SQLException Throws if there was a sql exception involved.
   */
  private Long getIdFromResultSet(ResultSet resultSet) throws SQLException {
    Long id = null;

    //Get the dataUser.
    if (resultSet.next()) {
      id = resultSet.getLong("id");
    }

    return id;
  }

  /**
   * The constructor that receives the autowired components.
   *
   * @param dbConfig        The dbms config.
   * @param mysqlDataSource The datasource object for dbms.
   * @param timcardDbConfig The timcard db config.
   * @param usersDbConfig   The users table config.
   */
  @Autowired
  public UsersDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, UsersDbConfig usersDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = usersDbConfig;
  }
}
