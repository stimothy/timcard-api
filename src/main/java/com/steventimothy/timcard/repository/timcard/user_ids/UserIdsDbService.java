package com.steventimothy.timcard.repository.timcard.user_ids;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataUserId;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.user_ids.config.UserIdsDbConfig;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>The RolePermissionsDbService Class</h1>
 * <p>This class is responsible for talking with the role_permissions table in the database.</p>
 */
@Slf4j
@Component
class UserIdsDbService extends TimcardDbService {

  /**
   * The configurations for the role_permissions table.
   */
  private UserIdsDbConfig dbConfig;

  DataUserId get() {

  }

  DataUserId get(Long id) {

  }

  DataUserId get(String user_id) {

  }

  Boolean update(DataUserId dataUserId, boolean used) {

  }

  /**
   * Frees up a user id in the database.
   * @param user_id The user id to free.
   * @throws DatabaseDataException throws if the data used in the query is bad.
   */
  void update(String user_id, boolean used)
      throws DatabaseDataException {

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dbConfig.getTableName() + " SET used = ? WHERE user_id = ?");
      preparedStatement.setBoolean(1, used);
      preparedStatement.setString(2, user_id);

      preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used in the query was bad.", ex);
    }
  }

  /**
   * Gets a new user id from the database.
   * @return The new user retrieved from the database.
   * @throws DatabaseDataException throws if the query was bad.
   */
  synchronized DataUserId get()
      throws DatabaseDataException {

    DataUserId dataUserId = null;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE used = FALSE LIMIT 1");

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the dataUser.
      if (resultSet.next()) {
        dataUserId = new DataUserId()
            .id(resultSet.getLong("id"))
            .user_id(resultSet.getString("user_id"))
            .used(resultSet.getBoolean("used"));

        setUsed(dataUserId.id(), connection);
      }

    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The query was bad to query the database.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataUserId;
  }

  /**
   * Gets a new user id from the database.
   * @return The new user retrieved from the database.
   * @throws DatabaseDataException throws if the query was bad.
   */
  synchronized DataUserId get(String user_id)
      throws DatabaseDataException {

    DataUserId dataUserId = null;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE user_id = ?");
      preparedStatement.setString(1, user_id);

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the dataUser.
      if (resultSet.next()) {
        dataUserId = new DataUserId()
            .id(resultSet.getLong("id"))
            .user_id(resultSet.getString("user_id"))
            .used(resultSet.getBoolean("used"));

        setUsed(dataUserId.id(), connection);
      }

    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The query was bad to query the database.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataUserId;
  }

  /**
   * Sets a new user id to used.
   * @param id The id to set to used.
   * @param connection The connection used to talk to the database.
   * @throws DatabaseDataException throws if the data used in the query was bad.
   */
  private void setUsed(Long id, Connection connection)
      throws DatabaseDataException {

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dbConfig.getTableName() + " SET used = TRUE WHERE id = ?");
      preparedStatement.setLong(1, id);

      int result = preparedStatement.executeUpdate();

      if (result != 1) {
        throw new DatabaseDataException("There was a problem setting the id to being used.");
      }
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used in the query was bad.", ex);
    }
  }

  /**
   * The constructor that receives the autowired components.
   *
   * @param dbConfig        The dbms config.
   * @param mysqlDataSource The datasource object for dbms.
   * @param timcardDbConfig The timcard db config.
   * @param userIdsDbConfig The user ids table config.
   */
  @Autowired
  UserIdsDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, UserIdsDbConfig userIdsDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = userIdsDbConfig;
  }
}
