package com.steventimothy.timcard.repository.timcard.permissions;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.permissions.config.PermissionsDbConfig;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * <h1>The PermissionsDbService Class</h1>
 * <p>This class is responsible for talking with the permissions table in the database.</p>
 */
@Slf4j
@Component
class PermissionsDbService extends TimcardDbService {

  /**
   * The configurations used for the permissions database.
   */
  private PermissionsDbConfig dbConfig;

  /**
   * Inserts a data permission into the database.
   * @param dataPermission The data permission to insert.
   * @return The id of the inserted data permission.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  Long insert(DataPermission dataPermission)
      throws DatabaseDataException {

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + this.dbConfig.getTableName() + " (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, dataPermission.name());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        dataPermission.id(resultSet.getLong(1));
      }
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return dataPermission.id();
  }

  /**
   * Gets a data permission from the database.
   * @param id The id of the data permission to retrieve.
   * @return The data permission retrieved from the database.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  DataPermission get(Long id)
      throws DatabaseDataException {

    DataPermission dataPermission;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + this.dbConfig.getTableName() + " WHERE id = ?");
      preparedStatement.setLong(1, id);

      dataPermission = get(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return dataPermission;
  }

  /**
   * Gets a data permission from the database.
   * @param name The name of the data permission to retrieve.
   * @return The data permission retrieved from the database.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  DataPermission get(String name)
      throws DatabaseDataException {

    DataPermission dataPermission;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + this.dbConfig.getTableName() + " WHERE name = ?");
      preparedStatement.setString(1, name);

      dataPermission = get(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return dataPermission;
  }

  /**
   * Updates a data permission in the database.
   * @param dataPermission The data permission containing the new values.
   * @return True if the data was updated.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  Boolean update(DataPermission dataPermission)
      throws DatabaseDataException {

    int rowsAffected;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + this.dbConfig.getTableName() + " SET name = ?, last_modified = ? WHERE id = ?");
      preparedStatement.setString(1, dataPermission.name());
      preparedStatement.setTimestamp(2, dataPermission.last_modified());
      preparedStatement.setLong(3, dataPermission.id());

      rowsAffected = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return (rowsAffected > 0);
  }

  /**
   * Deletes a data permission from the database.
   * @param id The id of teh data permission to delete.
   * @return True if the data was deleted.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  Boolean delete(Long id)
      throws DatabaseDataException {

    int rowsAffected;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + this.dbConfig.getTableName() + " WHERE id = ?");
      preparedStatement.setLong(1, id);

      rowsAffected = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return (rowsAffected > 0);
  }

  /**
   * Deletes a data permission from the database.
   * @param name The name of the data permission to delete.
   * @return True if the data was deleted successfully.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  Boolean delete(String name)
      throws DatabaseDataException {

    int rowsAffected;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + this.dbConfig.getTableName() + " WHERE name = ?");
      preparedStatement.setString(1, name);

      rowsAffected = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return (rowsAffected > 0);
  }

  /**
   * Gets the data permission from the database.
   * @param preparedStatement The prepared statement containing the query to retrieve the data.
   * @return The data permission retrieved from the database.
   * @throws SQLException Throws if there was a problem with the query to the database.
   */
  private DataPermission get(PreparedStatement preparedStatement)
      throws SQLException {

    DataPermission dataPermission = null;

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
      dataPermission = new DataPermission()
          .id(resultSet.getLong("id"))
          .name(resultSet.getString("name"))
          .date_created(resultSet.getTimestamp("date_created"))
          .last_modified(resultSet.getTimestamp("last_modified"));
    }

    return dataPermission;
  }


  /**
   * The constructor.
   *
   * @param dbConfig            The configurations for the entire database system.
   * @param timcardDbConfig     The configurations for the timcard database.
   * @param permissionsDbConfig The configurations for the permissions table.
   * @param mysqlDataSource     The mysql data source used to communicate with the database.
   */
  @Autowired
  PermissionsDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, PermissionsDbConfig permissionsDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = permissionsDbConfig;
  }
}
