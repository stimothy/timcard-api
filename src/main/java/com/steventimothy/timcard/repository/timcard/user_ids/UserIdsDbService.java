package com.steventimothy.timcard.repository.timcard.user_ids;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataUserId;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.user_ids.config.UserIdsDbConfig;
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

  /**
   * Frees up a user id in the database.
   * @param user_id The user id to free.
   */
  void update(String user_id) {
    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dbConfig.getTableName() + " SET used = FALSE WHERE user_id = ?");
      preparedStatement.setString(1, user_id);

      preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new UnsupportedOperationException("Exception not implemented yet.");
    }
  }

  /**
   * Gets a new user id from the database.
   * @return The new user retrieved from the database.
   */
  synchronized DataUserId get() {
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
      throw new UnsupportedOperationException("get Exception handling not implemented yet.");
    }

    //Close the connection.
    closeConnection(connection);

    return dataUserId;
  }

  /**
   * Sets a new user id to used.
   * @param id The id to set to used.
   * @param connection The connection used to talk to the database.
   */
  private void setUsed(Long id, Connection connection) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dbConfig.getTableName() + " SET used = TRUE WHERE id = ?");
      preparedStatement.setLong(1, id);

      int result = preparedStatement.executeUpdate();

      if (result != 1) {
        throw new UnsupportedOperationException("Couldn't set id to used and haven't supported this feature yet.");
      }
    }
    catch (SQLException ex) {
      throw new UnsupportedOperationException("get Exception handling not implemented yet.");
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
