package com.steventimothy.timcard.repository.timcard.roles;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataRole;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.roles.config.RolesDbConfig;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * <h1>The RolesDbService Class</h1>
 * <p>This class is responsible for talking with the roles table in the database.</p>
 */
@Slf4j
@Component
class RolesDbService extends TimcardDbService {

  /**
   * The config for the roles table.
   */
  private RolesDbConfig dbConfig;

  /**
   * Inserts a data role into the database.
   * @param dataRole The data role to insert.
   * @return The id of the inserted data role.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  Long insert(DataRole dataRole)
      throws DatabaseDataException {

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + this.dbConfig.getTableName() + " (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, dataRole.name());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        dataRole.id(resultSet.getLong(1));
      }
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return dataRole.id();
  }

  /**
   * Gets a data role from the database.
   * @param id The id of the data role to retrieve.
   * @return The data role retrieved from the database.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  DataRole get(Long id)
      throws DatabaseDataException {

    DataRole dataRole;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + this.dbConfig.getTableName() + " WHERE id = ?");
      preparedStatement.setLong(1, id);

      dataRole = get(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return dataRole;
  }

  /**
   * Gets a data role from the database.
   * @param name The name of the data role to retrieve.
   * @return The data role retrieved from the database.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  DataRole get(String name)
      throws DatabaseDataException {

    DataRole dataRole;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + this.dbConfig.getTableName() + " WHERE name = ?");
      preparedStatement.setString(1, name);

      dataRole = get(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return dataRole;
  }

  /**
   * Updates a data role in the database.
   * @param dataRole The data role containing the new values.
   * @return True if the data was updated.
   * @throws DatabaseDataException Throws if there was a problem with the query to the database.
   */
  Boolean update(DataRole dataRole)
      throws DatabaseDataException {

    int rowsAffected;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + this.dbConfig.getTableName() + " SET name = ?, last_modified = ? WHERE id = ?");
      preparedStatement.setString(1, dataRole.name());
      preparedStatement.setTimestamp(2, dataRole.last_modified());
      preparedStatement.setLong(3, dataRole.id());

      rowsAffected = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query to the database.", ex);
    }

    closeConnection(connection);

    return (rowsAffected > 0);
  }

  /**
   * Deletes a data role from the database.
   * @param id The id of the data role to delete.
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
   * Deletes a data role from the database.
   * @param name The name of the data role to delete.
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
   * Gets the data role from the database.
   * @param preparedStatement The prepared statement containing the query to retrieve the data.
   * @return The data role retrieved from the database.
   * @throws SQLException Throws if there was a problem with the query to the database.
   */
  private DataRole get(PreparedStatement preparedStatement)
      throws SQLException {

    DataRole dataRole = null;

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
      dataRole = new DataRole()
          .id(resultSet.getLong("id"))
          .name(resultSet.getString("name"))
          .date_created(resultSet.getTimestamp("date_created"))
          .last_modified(resultSet.getTimestamp("last_modified"));
    }

    return dataRole;
  }


  /**
   * Constructor.
   *
   * @param dbConfig        The configurations for the database system.
   * @param timcardDbConfig The configurations for the timcard database.
   * @param rolesDbConfig   The configurations for the roles table.
   * @param mysqlDataSource The mysql datasource used to connect to the database.
   */
  @Autowired
  RolesDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, RolesDbConfig rolesDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = rolesDbConfig;
  }
}
