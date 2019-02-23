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
   * The config for the permissions table.
   */
  private PermissionsDbConfig dbConfig;

  /**
   * Creates a data permission in the database.
   * @param name The name of the data permission.
   * @return The newly created data permission.
   * @throws DatabaseDataException throws if there was a conflict with the data
   * in the query.
   */
  DataPermission insert(String name)
      throws DatabaseDataException {

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " (name) VALUES(?)");
      preparedStatement.setString(1, name);

      preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query data.", ex);
    }

    closeConnection(connection);

    return get(name);
  }

  /**
   * Gets a DataPermission from the database by id.
   *
   * @param id The id of the permission
   * @return The data permission matching the id.
   * @throws DatabaseDataException throws if the data used to query the database was bad.
   */
  DataPermission get(Long id)
      throws DatabaseDataException {

    DataPermission dataPermission;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE id = ?");
      preparedStatement.setLong(1, id);

      dataPermission = getDataPermission(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataPermission;
  }

  /**
   * Gets a DataPermission from the database by name.
   *
   * @param name The name of the permission
   * @return The data permission matching the name.
   * @throws DatabaseDataException throws if the data used to query the database was bad.
   */
  DataPermission get(String name)
      throws DatabaseDataException {

    DataPermission dataPermission;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE name = ?");
      preparedStatement.setString(1, name);

      dataPermission = getDataPermission(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataPermission;
  }

  /**
   * Updates a data permission in the database.
   * @param dataPermission The data permission in the database to update.
   * @return True of the data permission was updated successfully.
   * @throws DatabaseDataException Throws if the data used in the query was bad.
   */
  Boolean update(DataPermission dataPermission)
      throws DatabaseDataException {

    int rowsAffected = 0;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dbConfig.getTableName() + " SET name = ? AND last_modified = ? WHERE id = ?");
      preparedStatement.setString(1, dataPermission.name());
      preparedStatement.setTimestamp(2, Timestamp.from(dataPermission.last_modified()));
      preparedStatement.setLong(3, dataPermission.id());

      rowsAffected = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    return (rowsAffected > 0);
  }

  /**
   * Deletes a data permission in the database.
   * @param id The id of the data permission.
   * @return True of the data permission was deleted successfully.
   * @throws DatabaseDataException Throws if the data used in the query was bad.
   */
  Boolean delete(Long id)
      throws DatabaseDataException {

    int rowsAffected = 0;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + dbConfig.getTableName() + " WHERE id = ?");
      preparedStatement.setLong(1, id);

      rowsAffected = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    return (rowsAffected > 0);
  }

  /**
   * Deletes a data permission in the database.
   * @param name The name of the data permission.
   * @return True of the data permission was deleted successfully.
   * @throws DatabaseDataException Throws if the data used in the query was bad.
   */
  Boolean delete(String name)
      throws DatabaseDataException {

    int rowsAffected = 0;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + dbConfig.getTableName() + " WHERE name = ?");
      preparedStatement.setString(1, name);

      rowsAffected = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    return (rowsAffected > 0);
  }

  /**
   * Gets the data permission from the executed query.
   * @param preparedStatement The prepared statement to query the database.
   * @return The Data permission retrieved from the database.
   * @throws SQLException Throws if something went wrong with the data query.
   */
  private DataPermission getDataPermission(PreparedStatement preparedStatement) throws SQLException {
    //Execute the statement
    ResultSet resultSet = preparedStatement.executeQuery();

    //Get the dataPermission.
    if (resultSet.next()) {
      return new DataPermission()
          .id(resultSet.getLong("id"))
          .name(resultSet.getString("name"))
          .date_created(resultSet.getTimestamp("date_created").toInstant())
          .last_modified(resultSet.getTimestamp("last_modified").toInstant());
    }
    else {
      return null;
    }
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
